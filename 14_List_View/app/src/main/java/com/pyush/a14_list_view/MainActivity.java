package com.pyush.a14_list_view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    int[] arrNo = new int[]{1, 2, 3, 4, 5, 6, 7};
    ArrayList<String> arrNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List View");

        listView = findViewById(R.id.listView);

        arrNames.add("Java");
        arrNames.add("Python");
        arrNames.add("React");
        arrNames.add(".Net");
        arrNames.add("Node.js");
        arrNames.add("HTML");
        arrNames.add("CSS");
        arrNames.add("JavaScript");
        arrNames.add("BootStrap");
        arrNames.add("Rust");
        arrNames.add("Next.js");
        arrNames.add("Flutter");
        arrNames.add("MySQL");
        arrNames.add("PHP");
        arrNames.add("Spring Boot");
        arrNames.add("Angular");
        arrNames.add("Firebase");
        arrNames.add("Azure");
        arrNames.add("Java");
        arrNames.add("Python");
        arrNames.add("React");
        arrNames.add(".Net");
        arrNames.add("Node.js");
        arrNames.add("HTML");
        arrNames.add("CSS");
        arrNames.add("JavaScript");
        arrNames.add("BootStrap");
        arrNames.add("Rust");
        arrNames.add("Next.js");
        arrNames.add("Flutter");
        arrNames.add("MySQL");
        arrNames.add("PHP");
        arrNames.add("Spring Boot");
        arrNames.add("Angular");
        arrNames.add("Firebase");
        arrNames.add("Azure");
        arrNames.add("Java");
        arrNames.add("Python");
        arrNames.add("React");
        arrNames.add(".Net");
        arrNames.add("Node.js");
        arrNames.add("HTML");
        arrNames.add("CSS");
        arrNames.add("JavaScript");
        arrNames.add("BootStrap");
        arrNames.add("Rust");
        arrNames.add("Next.js");
        arrNames.add("Flutter");
        arrNames.add("MySQL");
        arrNames.add("PHP");
        arrNames.add("Spring Boot");
        arrNames.add("Angular");
        arrNames.add("Firebase");
        arrNames.add("Azure");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 5) {
                    Toast.makeText(MainActivity.this, "Clicked First Item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
