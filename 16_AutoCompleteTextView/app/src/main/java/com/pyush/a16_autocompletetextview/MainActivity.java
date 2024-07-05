package com.pyush.a16_autocompletetextview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView  actView;
    ArrayList<String> arrA = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        actView = findViewById(R.id.actView);

        //Autocomplete Text View
        arrA.add("C");
        arrA.add("C++");
        arrA.add("Java");
        arrA.add("C#");
        arrA.add(".net");
        arrA.add("HTML");
        arrA.add("CSS");
        arrA.add("JavaScript");
        arrA.add("PHP");
        arrA.add("Pyhton");
        arrA.add("XML");
        arrA.add("R");
        arrA.add("Rust");
        arrA.add("Ruby");
        arrA.add("Go");
        arrA.add("Dart");
        arrA.add("Kotlin");
        arrA.add("Solidity");
        arrA.add("Swift");
        arrA.add("TypeScript");

        ArrayAdapter<String> actvAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrA);
        actView.setAdapter(actvAdapter);
        actView.setThreshold(1);




    }
}