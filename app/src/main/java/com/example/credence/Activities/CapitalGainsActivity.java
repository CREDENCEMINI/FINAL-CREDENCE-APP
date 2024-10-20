package com.example.credence.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.credence.R;

public class CapitalGainsActivity extends AppCompatActivity {

    private EditText capitalGainsInput;
    private TextView capitalGainsTaxOutput;
    private Button calculateCapitalGainsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capitalgain);

        // Binding views with their XML ids
        capitalGainsInput = findViewById(R.id.capitalGainsInput);
        capitalGainsTaxOutput = findViewById(R.id.capitalGainsTaxOutput);
        calculateCapitalGainsButton = findViewById(R.id.calculateCapitalGainsButton);

        // Set button click listener
        calculateCapitalGainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCapitalGainsTax();
            }
        });
    }

    // Function to calculate Capital Gains Tax
    private void calculateCapitalGainsTax() {
        // Get the capital gains entered by the user
        String capitalGainsStr = capitalGainsInput.getText().toString();

        // Check if input is not empty
        if (!capitalGainsStr.isEmpty()) {
            double capitalGains = Double.parseDouble(capitalGainsStr);

            double taxAmount;

            // Check if it's long-term or short-term capital gains
            if (isShortTerm(capitalGains)) {
                // Short-term capital gains tax (assume 15% tax rate)
                taxAmount = capitalGains * 0.15;
            } else {
                // Long-term capital gains tax (assume 10% tax rate)
                taxAmount = capitalGains * 0.10;
            }

            // Display the calculated tax in the output TextView
            capitalGainsTaxOutput.setText("₹ " + String.format("%.2f", taxAmount));
        } else {
            capitalGainsTaxOutput.setText("Please enter capital gains.");
        }
    }

    // Function to determine if the capital gains are short-term or long-term
    // You can add more conditions based on asset type and duration
    private boolean isShortTerm(double capitalGains) {
        // Placeholder condition for short-term (you can modify this logic as needed)
        // Assuming capital gains less than ₹ 1,00,000 is short-term
        return capitalGains < 100000;
    }
}
