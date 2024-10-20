package com.example.credence.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.credence.R;

public class PropertyTaxActivity extends AppCompatActivity {

    private EditText propertyValueInput;
    private TextView propertyTaxOutput;
    private Button calculatePropertyTaxButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propertytax); // Ensure this is the correct XML layout

        // Initialize views
        propertyValueInput = findViewById(R.id.propertyValueInput);
        propertyTaxOutput = findViewById(R.id.propertyTaxOutput);
        calculatePropertyTaxButton = findViewById(R.id.calculatePropertyTaxButton);

        // Set up button click listener
        calculatePropertyTaxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePropertyTax();
            }
        });
    }

    private void calculatePropertyTax() {
        String propertyValueStr = propertyValueInput.getText().toString();

        if (TextUtils.isEmpty(propertyValueStr)) {
            Toast.makeText(PropertyTaxActivity.this, "Please enter the property value", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse the input property value
            double propertyValue = Double.parseDouble(propertyValueStr);

            // Assuming a property tax rate of 1.2% (you can adjust this value)
            double taxRate = 0.002;
            double propertyTax = propertyValue * taxRate;

            // Display the calculated tax
            propertyTaxOutput.setText(String.format("₹ %.2f", propertyTax));

        } catch (NumberFormatException e) {
            Toast.makeText(PropertyTaxActivity.this, "Invalid property value entered", Toast.LENGTH_SHORT).show();
        }
    }
}
