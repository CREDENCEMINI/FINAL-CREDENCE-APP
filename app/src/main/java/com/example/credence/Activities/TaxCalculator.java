package com.example.credence.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.credence.R;

public class TaxCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test); // Updated to use test.xml

        // Find the LinearLayouts by their IDs
        LinearLayout incomeTaxLayout = findViewById(R.id.linear1);
        LinearLayout propertyTaxLayout = findViewById(R.id.linearLayout2);
        LinearLayout capitalGainsTaxLayout = findViewById(R.id.linear3);

        // Set click listeners for each LinearLayout
        incomeTaxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch MainActivity when Income Tax layout is clicked
                Intent intent = new Intent(TaxCalculator.this, IncomeTaxActivity.class);
                startActivity(intent);
            }
        });

        propertyTaxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch PropertyTaxActivity when Property Tax layout is clicked
                Intent intent = new Intent(TaxCalculator.this, PropertyTaxActivity.class);
                startActivity(intent);
            }
        });

        capitalGainsTaxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch CapitalGainsActivity when Capital Gains layout is clicked
                Intent intent = new Intent(TaxCalculator.this, CapitalGainsActivity.class);
                startActivity(intent);
            }
        });
    }
}
