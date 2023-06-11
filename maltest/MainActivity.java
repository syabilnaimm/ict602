package com.example.maltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText units;
    private EditText rebate;
    private Button calculateButton;
    private TextView total;
    private TextView finalCostTV;
    private Button aboutusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        units = findViewById(R.id.units);
        rebate = findViewById(R.id.rebate);
        calculateButton = findViewById(R.id.calculateButton);
        total = findViewById(R.id.total);
        aboutusButton = findViewById(R.id.aboutusButton);
        finalCostTV = findViewById(R.id.finalCostTV);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateElectricityBill();
            }
        });

        aboutusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileView();
            }
        });
    }

    private void calculateElectricityBill() {
        // Retrieve the input values from the user interface
        String unitsText = units.getText().toString();
        String rebateText = rebate.getText().toString();

        // Check if any input field is empty
        if (unitsText.isEmpty() || rebateText.isEmpty()) {
            // Display an error message
            total.setText("");
            finalCostTV.setText("");
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        int unitsUsed = Integer.parseInt(unitsText);
        float rebatePercentage = Float.parseFloat(rebateText);

        // Calculate the electricity bill
        float totalCharges = 0.0f;

        if (unitsUsed <= 200) {
            totalCharges = unitsUsed * 0.218f;
        } else if (unitsUsed <= 300) {
            totalCharges = 200 * 0.218f + (unitsUsed - 200) * 0.334f;
        } else if (unitsUsed <= 600) {
            totalCharges = 200 * 0.218f + 100 * 0.334f + (unitsUsed - 300) * 0.516f;
        } else if (unitsUsed > 600) {
            totalCharges = 200 * 0.218f + 100 * 0.334f + 300 * 0.516f + (unitsUsed - 600) * 0.546f;
        }

        float finalCost = totalCharges - (totalCharges * (rebatePercentage / 100.0f));

        // Display the result in the output fields
        total.setText(String.format("RM %.2f", totalCharges));
        finalCostTV.setText(String.format("RM %.2f", finalCost));
    }

    private void openProfileView() {
        View profileView = LayoutInflater.from(this).inflate(R.layout.profile, (ViewGroup) findViewById(android.R.id.content), false);
        setContentView(profileView);
    }

}
