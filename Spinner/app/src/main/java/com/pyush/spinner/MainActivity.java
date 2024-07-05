package com.pyush.spinner;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> arrIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar); // Use setSupportActionBar instead of setActionBar
        getSupportActionBar().setTitle("Spinner Demo App");

        spinner = findViewById(R.id.spinner);

        arrIds.add("Aadhar Card");
        arrIds.add("PAN Card");
        arrIds.add("Driving Licence");
        arrIds.add("Voter ID");
        arrIds.add("Ration Card");
        arrIds.add("Passport");
        arrIds.add("Visa");
        arrIds.add("Office ID Card");
        arrIds.add("Any Government ID");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrIds);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
}
