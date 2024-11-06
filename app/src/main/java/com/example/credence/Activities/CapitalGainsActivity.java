package com.example.credence.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.credence.R;

import java.text.DecimalFormat;

public class CapitalGainsActivity extends AppCompatActivity {

    private EditText capitalGainsInput;
    private TextView capitalGainsTaxOutput;
    private Button calculateCapitalGainsButton;
    private Spinner periodSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capitalgain);

        // Binding views with their XML ids
        capitalGainsInput = findViewById(R.id.capitalGainsInput);
        capitalGainsTaxOutput = findViewById(R.id.capitalGainsTaxOutput);
        calculateCapitalGainsButton = findViewById(R.id.calculateCapitalGainsButton);
        periodSpinner = findViewById(R.id.period);  // Spinner for selecting short-term/long-term

        // Set up the Spinner with options for Short-term and Long-term
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gains_period, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodSpinner.setAdapter(adapter);

        // Set button click listener
        calculateCapitalGainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCapitalGainsTax();
            }
        });

        // Add a TextWatcher to format the input with commas
        capitalGainsInput.addTextChangedListener(new TextWatcher() {
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
                    capitalGainsInput.removeTextChangedListener(this); // Remove listener to prevent infinite loop
                    capitalGainsInput.setText(formattedInput);
                    capitalGainsInput.setSelection(formattedInput.length()); // Move cursor to end
                    capitalGainsInput.addTextChangedListener(this); // Re-add listener
                }
            }
        });
    }

    // Function to calculate Capital Gains Tax
    private void calculateCapitalGainsTax() {
        // Get the capital gains entered by the user
        String capitalGainsStr = capitalGainsInput.getText().toString();

        // Check if input is not empty
        if (!capitalGainsStr.isEmpty()) {
            // Remove commas from the input and convert to double
            capitalGainsStr = capitalGainsStr.replace(",", "");
            double capitalGains = Double.parseDouble(capitalGainsStr);

            double taxAmount;

            // Get the selected option from the Spinner (Short-term or Long-term)
            String selectedPeriod = periodSpinner.getSelectedItem().toString();

            // Check if it's short-term or long-term capital gains based on Spinner selection
            if (selectedPeriod.equals("Short-term")) {
                // Short-term capital gains tax (assume 15% tax rate)
                taxAmount = capitalGains * 0.15;
            } else {
                // Long-term capital gains tax (assume 10% tax rate)
                taxAmount = capitalGains * 0.10;
            }

            // Format the tax amount with commas
            DecimalFormat formatter = new DecimalFormat("##,##,##0.00");
            String formattedTaxAmount = formatter.format(taxAmount);

            // Display the formatted tax in the output TextView
            capitalGainsTaxOutput.setText("₹ " + formattedTaxAmount);
        } else {
            capitalGainsTaxOutput.setText("Please enter capital gains.");
        }
    }
}
