package com.piyush.a39_crudapp_sqlite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText rollNoEditText, nameEditText, addressEditText, genderEditText;
    private Button addButton, updateButton, deleteButton, fetchButton, displayButton;
    private MyDBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollNoEditText = findViewById(R.id.rollNoEditText);
        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        genderEditText = findViewById(R.id.genderEditText);
        addButton = findViewById(R.id.addButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        fetchButton = findViewById(R.id.fetchButton);
        displayButton = findViewById(R.id.displayButton);

        dbHelper = new MyDBHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel student = new StudentModel();
                student.setRollNo(Integer.parseInt(rollNoEditText.getText().toString()));
                student.setName(nameEditText.getText().toString());
                student.setAddress(addressEditText.getText().toString());
                student.setGender(genderEditText.getText().toString());
                dbHelper.addStudent(student);
                Toast.makeText(MainActivity.this, "Student Added", Toast.LENGTH_SHORT).show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel student = new StudentModel();
                student.setRollNo(Integer.parseInt(rollNoEditText.getText().toString()));
                student.setName(nameEditText.getText().toString());
                student.setAddress(addressEditText.getText().toString());
                student.setGender(genderEditText.getText().toString());
                dbHelper.updateStudent(student);
                Toast.makeText(MainActivity.this, "Student Updated", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rollNo = Integer.parseInt(rollNoEditText.getText().toString());
                dbHelper.deleteStudent(rollNo);
                Toast.makeText(MainActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StudentModel> students = dbHelper.fetchStudents();
                for (StudentModel student : students) {
                    Log.d("Student Info", "Roll No: " + student.getRollNo() + ", Name: " + student.getName() + ", Address: " + student.getAddress() + ", Gender: " + student.getGender());
                }
            }
        });

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayStudentsActivity.class);
                startActivity(intent);
            }
        });
    }
}
