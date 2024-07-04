package com.pyush.a11_bundle_passing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String studentName = intent.getStringExtra("Student Name");
        int rollNo = intent.getIntExtra("Enrollment Number", 0);

        TextView textView = findViewById(R.id.tInfo);
        textView.setText("RollNo: " + rollNo + ", Name: " + studentName);

        // Set the title of the ActionBar
        getSupportActionBar().setTitle(title);
    }
}
