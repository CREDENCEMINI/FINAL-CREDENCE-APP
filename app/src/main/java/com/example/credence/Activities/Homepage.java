package com.example.credence.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.credence.R;

public class Homepage extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout spentLogBookButton = findViewById(R.id.spent_logbook_button);
        spentLogBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Homepage.this, Logbook.class);
                startActivity(intent);
            }
        });

        LinearLayout financialToDoList = findViewById(R.id.expense_graph_button);
        financialToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Homepage.this, FinancialToDoList.class);
                startActivity(intent);
            }
        });

        LinearLayout taxCalculator = findViewById(R.id.tax_calculator_button);
        taxCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Homepage.this, TaxCalculator.class);
                startActivity(intent);
            }
        });

        LinearLayout goalRec = findViewById(R.id.goal_button);
        goalRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Homepage.this, GoalRecPage.class);
                startActivity(intent);
            }
        });

        LinearLayout profile = findViewById(R.id.profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Homepage.this, Profile.class);
                startActivity(intent);
            }
        });

        LinearLayout currencycon = findViewById(R.id.currency_converter_button);
        currencycon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Homepage.this, CurrencyCov.class);
                startActivity(intent);
            }
        });



    }
}