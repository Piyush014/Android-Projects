package com.piyush.a41_simplesharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name, sname;
    String hint1, hint2;
    Button button;
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit1);
        sname = findViewById(R.id.edit2);
        button = findViewById(R.id.button);
        res = findViewById(R.id.res);
        hint1 = "Enter Your Name";
        hint2 = "Enter Your Surname";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concated();
            }
        });
    }


    public void concated() {
        String nameText = name.getText().toString();
        String snameText = sname.getText().toString();
        String c = nameText + " " + snameText;
        res.setText(c);

        SharedPreferences sp =  getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("res",c);
        editor.apply();
        name.setText(hint1);
        sname.setText(hint2);
    }
    @Override
    protected void onResume() {
        super.onResume();

        //Fetch the data from stored reference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String result = sh.getString("res", "");
        res.setText(result);
    }

    @Override
    protected void onPause() {
        super.onPause();
        name.setText("");
        sname.setText("");
    }
}