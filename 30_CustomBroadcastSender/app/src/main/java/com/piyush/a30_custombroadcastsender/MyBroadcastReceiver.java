package com.piyush.a30_custombroadcastsender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            if (!noConnectivity) {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            }
        } else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
            Toast.makeText(context, "Time incremented", Toast.LENGTH_SHORT).show();
        }
    }
}
