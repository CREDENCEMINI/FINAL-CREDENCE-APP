package com.example.credence.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.credence.R;

public class CreateAccConfimPage extends AppCompatActivity {
    private Button B4;
    public static final String NAME = "NAME1";
    public static final String PHONE = "PHONE1";
    public static final String EMAIL = "EMAIL1";
    private TextView nameTextView, emailTextView, phoneTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_acc_confirm_page);
        nameTextView = findViewById(R.id.naam); // Replace with actual IDs
        emailTextView = findViewById(R.id.mail1);
        phoneTextView = findViewById(R.id.mobile1);


        String name=getIntent().getStringExtra("NAME1");
        String phone = getIntent().getStringExtra("PHONE1");
        String email = getIntent().getStringExtra("EMAIL1");

        nameTextView.setText(name);
        emailTextView.setText(email);
        phoneTextView.setText(phone);

        String name1= nameTextView.getText().toString();
        String phone1 = phoneTextView.getText().toString();
        String email1 = emailTextView.getText().toString();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        B4=findViewById(R.id.Cont1);
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CreateAccConfimPage.this, LoginSignupPage.class);
                intent.putExtra("NAME2",name1);
                intent.putExtra("PHONE2",phone1);
                intent.putExtra("EMAIL2",email1);
                startActivity(intent);
                finish();
            }
        });
    }
}