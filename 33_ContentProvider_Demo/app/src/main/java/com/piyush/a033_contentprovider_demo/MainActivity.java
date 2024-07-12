package com.piyush.a033_contentprovider_demo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = findViewById(R.id.edit1);
        e2 = findViewById(R.id.edit2);
    }

    public void doSaveContent(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("emp_name", e1.getText().toString());
        contentValues.put("profile", e2.getText().toString());
        Uri uri = getContentResolver().insert(CompanyProvider.CONTENT_URI, contentValues);
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
    }

    public void doLoadContent(View view) {
        Cursor cr = getContentResolver().query(CompanyProvider.CONTENT_URI, null, null, null, "_id");
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
