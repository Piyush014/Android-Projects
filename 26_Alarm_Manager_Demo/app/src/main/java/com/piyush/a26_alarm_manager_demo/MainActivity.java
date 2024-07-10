package com.piyush.a26_alarm_manager_demo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final int ALARM_REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.edtTime);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        findViewById(R.id.btnSet).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    int time = Integer.parseInt(editText.getText().toString());
                    long triggerTime = System.currentTimeMillis() + (time * 1000);
                    Intent iBroadCast = new Intent(MainActivity.this, MyReceiver.class);
                    PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, ALARM_REQ_CODE, iBroadCast, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pi);

                    Toast.makeText(MainActivity.this, "Alarm set for " + time + " seconds from now.", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
