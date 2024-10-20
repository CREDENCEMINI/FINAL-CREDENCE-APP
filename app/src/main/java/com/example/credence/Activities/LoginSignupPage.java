package com.example.credence.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.credence.R;

public class LoginSignupPage extends AppCompatActivity {
    private Button Buttonlogin;
    private Button BUTTONSIGNUP;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_signup_page);

        Buttonlogin = findViewById(R.id.ButtonLogin);
        Buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginSignupPage.this, "Opening Login Page", Toast.LENGTH_SHORT).show();
                intent = new Intent(LoginSignupPage.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        BUTTONSIGNUP = findViewById(R.id.BUTTONSIGNUP);
        BUTTONSIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginSignupPage.this, "Opening Signup Page", Toast.LENGTH_SHORT).show();
                intent = new Intent(LoginSignupPage.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
