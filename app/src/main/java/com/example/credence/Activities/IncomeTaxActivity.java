package com.example.credence.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.credence.R;

import java.text.DecimalFormat;

public class IncomeTaxActivity extends AppCompatActivity {

    EditText incomeInput;
    Button calculateButton;
    TextView taxOutput;
    Spinner genderSpinner, ageGroupSpinner, deductionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_tax);

        // Initialize views
        incomeInput = findViewById(R.id.incomeInput);
        calculateButton = findViewById(R.id.calculateButton);
        taxOutput = findViewById(R.id.taxOutput);
        genderSpinner = findViewById(R.id.genderSpinner);
        ageGroupSpinner = findViewById(R.id.ageGroupSpinner);
        deductionSpinner = findViewById(R.id.deductionSpinner);

        // Set up spinners with options
        String[] genders = {"Male", "Female"};
        String[] ageGroups = {"Below 60", "60-80", "80+"};
        String[] deductions = {"None", "80C Deduction"};

        genderSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders));
        ageGroupSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ageGroups));
        deductionSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, deductions));

        // Set TextWatcher to format the income input with commas in the Indian style
        incomeInput.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    incomeInput.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[,]", "");
                    double parsed;
                    try {
                        parsed = Double.parseDouble(cleanString);
                    } catch (NumberFormatException e) {
                        parsed = 0.0;
                    }

                    // Format the parsed number in Indian comma format
                    DecimalFormat indianFormat = new DecimalFormat("##,##,###");
                    String formatted = indianFormat.format(parsed);

                    current = formatted;
                    incomeInput.setText(formatted);
                    incomeInput.setSelection(formatted.length());

                    incomeInput.addTextChangedListener(this);
                }
            }
        });

        // Set onClickListener for the button to calculate tax
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incomeStr = incomeInput.getText().toString();
                double income;
                try {
                    // Remove commas before parsing the input
                    income = Double.parseDouble(incomeStr.replace(",", ""));
                } catch (NumberFormatException e) {
                    taxOutput.setText("Invalid Input");
                    return;
                }

                // Get selected options
                String gender = genderSpinner.getSelectedItem().toString();
                String ageGroup = ageGroupSpinner.getSelectedItem().toString();
                String deduction = deductionSpinner.getSelectedItem().toString();

                // Calculate tax
                double tax = calculateTax(income, ageGroup, gender, deduction);

                // Format the tax output with Indian comma style
                DecimalFormat indianFormat = new DecimalFormat("##,##,###");
                String formattedTax = "₹" + indianFormat.format(tax);

                // Display the formatted tax in the output TextView
                taxOutput.setText(formattedTax);
            }
        });
    }

    private double calculateTax(double income, String ageGroup, String gender, String deduction) {
        // Define exemption limits based on age group
        double exemptionLimit = ageGroup.equals("80+") ? 500000 : ageGroup.equals("60-80") ? 300000 : 250000;

        // Apply 80C deduction if selected
        double deductionAmount = deduction.equals("80C Deduction") ? 15000 : 0;
        double taxableIncome = income - deductionAmount;

        // Ensure taxable income is reduced by exemption limit
        if (taxableIncome <= exemptionLimit) {
            return 0; // No tax if income is below or equal to exemption limit
        } else {
            taxableIncome -= exemptionLimit;
        }

        double tax = 0;

        // Apply progressive tax slabs to the remaining taxable income
        if (taxableIncome > 0) {
            if (taxableIncome <= 250000) {
                tax += taxableIncome * 0.05;  // 5% for income up to 2.5L
            } else {
                tax += 250000 * 0.05;
                if (taxableIncome <= 500000) {
                    tax += (taxableIncome - 250000) * 0.10;  // 10% for next 2.5L
                } else {
                    tax += 250000 * 0.10;
                    if (taxableIncome <= 750000) {
                        tax += (taxableIncome - 500000) * 0.15;  // 15% for next 2.5L
                    } else {
                        tax += 250000 * 0.15;
                        if (taxableIncome <= 1000000) {
                            tax += (taxableIncome - 750000) * 0.20;  // 20% for next 2.5L
                        } else {
                            tax += 250000 * 0.20;
                            if (taxableIncome <= 1250000) {
                                tax += (taxableIncome - 1000000) * 0.25;  // 25% for next 2.5L
                            } else {
                                tax += 250000 * 0.25;
                                if (taxableIncome <= 1500000) {
                                    tax += (taxableIncome - 1250000) * 0.30;  // 30% for next 2.5L
                                } else {
                                    tax += 250000 * 0.30;
                                    tax += (taxableIncome - 1500000) * 0.30;  // 30% for income over 15L
                                }
                            }
                        }
                    }
                }
            }
        }

        return tax;
    }
}
