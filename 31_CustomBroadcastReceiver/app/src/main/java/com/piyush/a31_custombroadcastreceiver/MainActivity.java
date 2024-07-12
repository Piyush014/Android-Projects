package com.piyush.a31_custombroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private MyDemoReceiver myDemoReceiver = new MyDemoReceiver();
    private String receivedData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editText);

        IntentFilter intentFilter = new IntentFilter("com.piyush.a31_custombroadcastreceiver.ACTION_SEND");
        registerReceiver(myDemoReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myDemoReceiver);
    }

    public class MyDemoReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.piyush.a31_custombroadcastreceiver.ACTION_SEND".equals(intent.getAction())) {
                receivedData = intent.getStringExtra("com.piyush.EXTRA_DATA");
                Toast.makeText(context, "Broadcast Received", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void receiveBroadcast(View view) {
        mEditText.setText(receivedData);
    }
}
