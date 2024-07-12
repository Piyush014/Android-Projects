package com.piyush.a034_secondcontentprovider_demo;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final Uri CONTENT_URI = Uri.parse("content://com.piyush.my.company.provider/emp");
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLoading();
            }
        });
    }

    private void doLoading() {
        Cursor cr = getContentResolver().query(CONTENT_URI, null, null, null, "_id");
        if (cr != null) {
            StringBuilder stringBuilder = new StringBuilder();
            while (cr.moveToNext()) {
                int id = cr.getInt(0);
                String s1 = cr.getString(1);
                String s2 = cr.getString(2);
                stringBuilder.append(id).append(" ").append(s1).append(" ").append(s2).append("\n");
            }
            cr.close();
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cursor is null", Toast.LENGTH_SHORT).show();
        }
    }
}
