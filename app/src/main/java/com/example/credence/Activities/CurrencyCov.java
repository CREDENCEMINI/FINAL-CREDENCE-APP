package com.example.credence.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.credence.R;
import com.example.credence.databinding.CurrencyconvActivityBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CurrencyCov extends AppCompatActivity {

    CurrencyconvActivityBinding binding;

    double fromValue = 0.0;
    String fromUnit = "";
    double toValue = 0.0;
    String toUnit = "";

    private List<String> units = Arrays.asList(
            "JPY", "CAD", "AUD", "INR", "SGD", "HKD", "USD"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CurrencyconvActivityBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        initComponents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initComponents() {
        Collections.sort(units);

        // Custom adapter for Spinner to set text color
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, units) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                ((TextView) view).setTextColor(Color.BLACK); // Set text color to white
                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView) view).setTextColor(Color.BLACK); // Set text color to white
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.fromUnit.setAdapter(arrayAdapter);
        binding.toUnit.setAdapter(arrayAdapter);

        binding.convertButton.setOnClickListener(view -> {
            try {
                fromValue = Double.parseDouble(binding.fromValue.getText().toString());
                fromUnit = binding.fromUnit.getSelectedItem().toString();
                toUnit = binding.toUnit.getSelectedItem().toString();
                convertValue();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void convertValue() {
        try {
            // Conversion logic
            if (fromUnit.equalsIgnoreCase("INR") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue / 84.06;
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("INR")) {
                toValue = fromValue * 84.06;
            }
            if (fromUnit.equalsIgnoreCase("INR") && toUnit.equalsIgnoreCase("JPY")) {
                toValue = fromValue / 0.56;
            } else if (fromUnit.equalsIgnoreCase("JPY") && toUnit.equalsIgnoreCase("INR")) {
                toValue = fromValue * 0.56;
            }
            if (fromUnit.equalsIgnoreCase("INR") && toUnit.equalsIgnoreCase("CAD")) {
                toValue = fromValue / 60.49;
            } else if (fromUnit.equalsIgnoreCase("CAD") && toUnit.equalsIgnoreCase("INR")) {
                toValue = fromValue * 60.49;
            }
            if(fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue/55.48;

            } else if (fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue*55.48;
            }
            if(fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue/63.60;

            } else if (fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue*63.60;
            }
            if(fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue/10.76;

            } else if (fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue*10.76;
            }
            if(fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("PHP"))
            {
                toValue=fromValue/1.5;

            } else if (fromUnit.equalsIgnoreCase("PHP")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue*1.5;
            }/////////////////////
            if(fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue/152.99;

            } else if (fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue*152.99;
            }
            if(fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue/1.82;

            } else if (fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue*1.82;
            }

            if(fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue/110.03;

            } else if (fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue*110.03;
            }
            if(fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue/100.99;

            } else if (fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue*100.99;
            }
            if(fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue/115.43;

            } else if (fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue*115.43;
            }
            if(fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue/19.68;

            } else if (fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue*19.68;
            }////////////
            if(fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue/1.35;

            } else if (fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue*1.35;
            }
            if(fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue/0.017;

            } else if (fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue*0.017;
            }

            if(fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue/0.0092;

            } else if (fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue*0.0092;
            }
            if(fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue/0.93;

            } else if (fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue*0.93;
            }
            if(fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue/1.05;

            } else if (fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue*1.05;
            }
            if(fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue/0.17;

            } else if (fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue*0.17;
            }//////////////
            if(fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue/1.52;

            } else if (fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue*1.52;
            }
            if(fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue/0.017;

            } else if (fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue*0.017;
            }

            if(fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue/1.08;

            } else if (fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue*1.08;
            }
            if(fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue/0.0099;

            } else if (fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue*0.0099;
            }
            if(fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue/1.13;

            } else if (fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue*1.13;
            }
            if(fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue/0.19;

            } else if (fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue*0.19;
            }/////////////
            if(fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue/1.32;

            } else if (fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue*1.32;
            }
            if(fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue/0.015;

            } else if (fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue*0.015;
            }

            if(fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue/0.95;

            } else if (fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue*0.95;
            }
            if(fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue/0.88;

            } else if (fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue*0.88;
            }
            if(fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue/0.0090;

            } else if (fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue*0.0090;
            }
            if(fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue/0.17;

            } else if (fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue*0.17;
            }///////////////////
            if(fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue/7.79;

            } else if (fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue*7.79;
            }
            if(fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue/0.093;

            } else if (fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue*0.093;
            }

            if(fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue/5.59;

            } else if (fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue*5.59;
            }
            if(fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue/5.13;

            } else if (fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue*5.13;
            }
            if(fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue/5.88;

            } else if (fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue*5.88;
            }
            if(fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue/0.054;

            } else if (fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue*0.054;
            }//////////////////
            if(fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("JPY"))
            {
                toValue=fromValue/0.0069;

            } else if (fromUnit.equalsIgnoreCase("JPY")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue*0.0069;
            }
            if(fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("INR"))
            {
                toValue=fromValue/0.012;

            } else if (fromUnit.equalsIgnoreCase("INR")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue*0.012;
            }

            if(fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("CAD"))
            {
                toValue=fromValue/0.74;

            } else if (fromUnit.equalsIgnoreCase("CAD")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue*0.74;
            }
            if(fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("AUD"))
            {
                toValue=fromValue/0.68;

            } else if (fromUnit.equalsIgnoreCase("AUD")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue*0.68;
            }
            if(fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("SGD"))
            {
                toValue=fromValue/0.77;

            } else if (fromUnit.equalsIgnoreCase("SGD")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue*0.77;
            }
            if(fromUnit.equalsIgnoreCase("USD")&& toUnit.equalsIgnoreCase("HKD"))
            {
                toValue=fromValue/0.13;

            } else if (fromUnit.equalsIgnoreCase("HKD")&& toUnit.equalsIgnoreCase("USD"))
            {
                toValue=fromValue*0.13;
            }//////////////
            // Additional conversion conditions here...

            binding.toValue.setText(String.valueOf(toValue));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
