package com.pyush.a12_bmi_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        EditText editWeight, editHeightFt, editHeightIn;
        Button btnCalculate;
        TextView txtResult;

        editWeight = findViewById(R.id.edtWeight);
        editHeightFt = findViewById(R.id.editHeightFt);
        editHeightIn = findViewById(R.id.editHeightIn);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Parsing the input values
                    int wt = Integer.parseInt(editWeight.getText().toString());
                    int ft = Integer.parseInt(editHeightFt.getText().toString());
                    int in = Integer.parseInt(editHeightIn.getText().toString());

                    // Calculating the total height in inches
                    int totalIn = ft * 12 + in;
                    // Converting height to centimeters
                    double totalCm = totalIn * 2.54;
                    // Converting height to meters
                    double totalM = totalCm / 100;
                    // Calculating BMI
                    double bmi = wt / (totalM * totalM);

                    // Displaying the appropriate result based on BMI value
                    if (bmi >= 30) {
                        txtResult.setText("You are Obese");
                    } else if (bmi >= 25) {
                        txtResult.setText("You are Overweight");
                    } else if (bmi >= 18.5) {
                        txtResult.setText("You're Healthy");
                    } else {
                        txtResult.setText("You are Underweight");
                    }
                } catch (NumberFormatException e) {
                    // Handling empty or invalid input
                    txtResult.setText("Please enter valid numbers");
                }
            }
        });

    }
}
