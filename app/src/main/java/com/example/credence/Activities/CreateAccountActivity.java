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

import com.example.credence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {

    EditText signupName, signupPhone, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    private ProgressBar progress1;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Optionally, handle a case where you want to redirect logged-in users.
        // If you want to keep users logged in, you can uncomment the following lines:
        // if (currentUser != null) {
        //     Intent intent = new Intent(MainActivity2_may.this, MainGoal1_may.class);
        //     startActivity(intent);
        //     finish();
        // }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);

        signupName = findViewById(R.id.inputname1);
        signupPhone = findViewById(R.id.inputphone1);
        signupEmail = findViewById(R.id.inputemail1);
        signupPassword = findViewById(R.id.inputpassword1);
        loginRedirectText = findViewById(R.id.LogIn1);
        signupButton = findViewById(R.id.Signup1);
        progress1 = findViewById(R.id.progress1);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress1.setVisibility(View.VISIBLE); // Show progress bar
                String name = signupName.getText().toString();
                String phone = signupPhone.getText().toString();
                String email = signupEmail.getText().toString();
                String password = signupPassword.getText().toString();

                if (validateInputs(name, phone, email, password)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    String userId = firebaseUser.getUid();
                                    User user = new User(userId, name, phone, email);

                                    reference.child(userId).setValue(user).addOnCompleteListener(task1 -> {
                                        progress1.setVisibility(View.GONE); // Hide progress bar
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(CreateAccountActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                            // Do not automatically log in the user, show a message instead
                                            Toast.makeText(CreateAccountActivity.this, "Please log in now.", Toast.LENGTH_SHORT).show();
                                            // Optionally clear input fields
                                            signupName.setText("");
                                            signupPhone.setText("");
                                            signupEmail.setText("");
                                            signupPassword.setText("");
                                            // Navigate to the next activity if needed
                                            Intent intent = new Intent(CreateAccountActivity.this, CreateAccConfimPage.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(CreateAccountActivity.this, "Database Registration Failed: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    progress1.setVisibility(View.GONE); // Hide progress bar on failure
                                    Toast.makeText(CreateAccountActivity.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    progress1.setVisibility(View.GONE); // Hide progress bar if input validation fails
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInputs(String name, String phone, String email, String password) {
        if (name.isEmpty() || name.length() < 7) {
            signupName.setError("Username is not valid");
            signupName.requestFocus();
            return false;
        }
        if (phone.isEmpty() || phone.length() != 10) {
            signupPhone.setError("Enter a valid phone number");
            signupPhone.requestFocus();
            return false;
        }
        if (email.isEmpty() || !email.contains("@")) {
            signupEmail.setError("Email is not valid");
            signupEmail.requestFocus();
            return false;
        }
        if (password.isEmpty() || password.length() < 7) {
            signupPassword.setError("Password must have at least 7 characters");
            signupPassword.requestFocus();
            return false;
        }
        return true;
    }

    public static class User {
        public String userId;
        public String name;
        public String phone;
        public String email;

        public User() {} // Default constructor required for calls to DataSnapshot.getValue(User.class)

        public User(String userId, String name, String phone, String email) {
            this.userId = userId;
            this.name = name;
            this.phone = phone;
            this.email = email;
        }
    }
}
