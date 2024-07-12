package com.piyush.a31_custombroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyDemoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.piyush.a31_custombroadcastreceiver.ACTION_SEND".equals(intent.getAction())){
            String extraData = intent.getStringExtra("com.piyush.EXTRA_DATA");
            Toast.makeText(context, "From Receiver: "+extraData, Toast.LENGTH_LONG).show();
        }
    }


}

