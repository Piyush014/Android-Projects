package com.piyush.a32_viewmodel_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText edt;
    private MainActivityViewModel viewModel; // Use the correct ViewModel type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        edt = findViewById(R.id.editText);

        // Correctly instantiate the ViewModel
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Set initial value from ViewModel
        edt.setText(String.valueOf(viewModel.getNumber()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addNumber(); // Increment the number in ViewModel
                edt.setText(String.valueOf(viewModel.getNumber())); // Update EditText
            }
        });
    }
}
