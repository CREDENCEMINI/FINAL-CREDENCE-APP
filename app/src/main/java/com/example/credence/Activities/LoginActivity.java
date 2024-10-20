package com.example.credence.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.credence.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private Button B2; // Login Button
    private Button B5; // Signup Button
    private Button BF;  // Forgot Password Button
    private TextView warnText; // Warning TextView
    private FirebaseAuth mAuth; // Firebase Authentication instance
    private ProgressBar progress2; // Progress Bar
    private EditText inputemail2; // Email Input
    private EditText inputpassword2; // Password Input

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginActivity.this, LoginConfirmationPage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        B2 = findViewById(R.id.button3);
        B5 = findViewById(R.id.Signup3);
        BF = findViewById(R.id.BForgot);
        inputemail2 = findViewById(R.id.inputemail2);
        inputpassword2 = findViewById(R.id.inputpassword2);
        progress2 = findViewById(R.id.progress2);
        warnText = findViewById(R.id.warn1);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2_1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle forgot password button click
        BF.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        // Handle login button click
        B2.setOnClickListener(view -> {
            progress2.setVisibility(View.VISIBLE);
            String email = inputemail2.getText().toString().trim();
            String password = inputpassword2.getText().toString().trim();

            if (isValidInput(email, password)) {
                warnText.setVisibility(View.GONE); // Hide warning message

                // Using Firebase Authentication for login
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            progress2.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                // Successfully logged in
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, LoginConfirmationPage.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Login failed
                                warnText.setVisibility(View.VISIBLE);
                                warnText.setText("Incorrect email or password.");
                            }
                        });
            } else {
                progress2.setVisibility(View.GONE);
            }
        });

        // Handle signup button click
        B5.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidInput(String email, String password) {
        if (email.isEmpty() || !email.contains("@")) {
            showError(inputemail2, "Email is not valid");
            return false;
        }
        if (password.isEmpty() || password.length() < 7) {
            showError(inputpassword2, "Password must have atleast 7 characters");
            return false;
        }
        return true;
    }

    private void showError(EditText input, String errorMessage) {
        input.setError(errorMessage);
        input.requestFocus();
    }
}
