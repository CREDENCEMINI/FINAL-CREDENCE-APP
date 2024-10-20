package com.example.credence.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.example.credence.R;

public class IncomeTaxActivity extends AppCompatActivity {

    EditText incomeInput;
    Button calculateButton;
    TextView taxOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_tax);

        // Initialize views
        incomeInput = findViewById(R.id.incomeInput);
        calculateButton = findViewById(R.id.calculateButton);
        taxOutput = findViewById(R.id.taxOutput);

        // Set onClickListener for the button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incomeStr = incomeInput.getText().toString();
                double income;
                try {
                    // Convert the input string to a double
                    income = Double.parseDouble(incomeStr);
                } catch (NumberFormatException e) {
                    // Handle non-numeric input
                    taxOutput.setText("Invalid Input: Please enter a valid number.");
                    return;
                }

                // Calculate tax
                double tax = calculateTax(income);
                taxOutput.setText("₹" + tax);
            }
        });
    }

    // Function to calculate income tax based on Indian tax slabs
    private double calculateTax(double income) {
        double tax = 0;

        if (income <= 250000) {
            tax = 0;
        } else if (income <= 500000) {
            tax = (income - 250000) * 0.05;
        } else if (income <= 750000) {
            tax = 12500 + (income - 500000) * 0.10;
        } else if (income <= 1000000) {
            tax = 37500 + (income - 750000) * 0.15;
        } else if (income <= 1250000) {
            tax = 75000 + (income - 1000000) * 0.20;
        } else if (income <= 1500000) {
            tax = 125000 + (income - 1250000) * 0.25;
        } else {
            tax = 187500 + (income - 1500000) * 0.30;
        }

        return tax;
    }
}
