package com.piyush.a30_custombroadcastsender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText mEditText;
    private BroadcastReceiver mInnerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.piyush.a31_custombroadcastreceiver.ACTION_SEND".equals(intent.getAction())) {
                String intentExtra = intent.getStringExtra("com.piyush.EXTRA_DATA");
                mTextView.setText("Inner Broadcast Receiver: " + intentExtra);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mEditText = findViewById(R.id.editText);

        // Register the inner receiver for specific custom broadcast action
        IntentFilter intentFilter = new IntentFilter("com.piyush.a31_custombroadcastreceiver.ACTION_SEND");
        registerReceiver(mInnerReceiver, intentFilter);
    }

    public void sendBroadcast(View view) {
        // Get the data from the EditText
        String dataToSend = mEditText.getText().toString();

        // Send a custom broadcast with some extra data
        Intent intent = new Intent("com.piyush.a31_custombroadcastreceiver.ACTION_SEND");
        intent.putExtra("com.piyush.EXTRA_DATA", dataToSend);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the inner receiver to avoid memory leaks
        unregisterReceiver(mInnerReceiver);
    }
}
