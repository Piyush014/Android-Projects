package com.piyush.a19_custom_toast_app;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Custom Toast
                Toast toast = new Toast(getApplicationContext());
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast_layout, (ViewGroup) findViewById(R.id.viewContent));
                toast.setView(layout);
                TextView txtMsg = layout.findViewById(R.id.txtMsg);
                txtMsg.setText("Message Sent Successfully");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0,100);
                toast.show();

                
            }
        });
    }
}
