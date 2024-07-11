package com.piyush.a28_broadcast_receiver_intro_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Broadcast Receiver is Triggered");
        Toast.makeText(context, "Broadcast Receiver is Triggered.", Toast.LENGTH_SHORT).show();
    }
}
