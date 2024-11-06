package com.example.credence.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.credence.R;

import java.text.DecimalFormat;

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

        // Add TextWatcher to format input with commas
        propertyValueInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int after) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if (!input.equals("")) {
                    // Remove any commas
                    input = input.replace(",", "");
                    // Format the input with commas
                    DecimalFormat formatter = new DecimalFormat("##,##,##0");
                    String formattedInput = formatter.format(Double.parseDouble(input));
                    // Set the formatted input back to the EditText
                    propertyValueInput.removeTextChangedListener(this); // Remove listener to prevent infinite loop
                    propertyValueInput.setText(formattedInput);
                    propertyValueInput.setSelection(formattedInput.length()); // Move cursor to end
                    propertyValueInput.addTextChangedListener(this); // Re-add listener
                }
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
            // Remove commas from the input and parse it
            propertyValueStr = propertyValueStr.replace(",", "");
            double propertyValue = Double.parseDouble(propertyValueStr);

            // Set the tax rate (example: 0.002 for property tax rate)
            double taxRate = 0.002;
            double propertyTax = propertyValue * taxRate;

            // Format the calculated property tax with commas
            DecimalFormat taxFormatter = new DecimalFormat("##,##,##0.00");
            String formattedTax = taxFormatter.format(propertyTax);

            // Display the formatted tax in the output TextView
            propertyTaxOutput.setText(String.format("₹ %s", formattedTax));

        } catch (NumberFormatException e) {
            Toast.makeText(PropertyTaxActivity.this, "Invalid property value entered", Toast.LENGTH_SHORT).show();
        }
    }
}
