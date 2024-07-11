package com.piyush.a29_receive_send_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    private static final String PREFS_NAME = "SMSReceiverPrefs";
    private static final String KEY_MESSAGE_RECEIVED = "message_received";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                        String mobNo = message.getDisplayOriginatingAddress();
                        String msg = message.getDisplayMessageBody();
                        Log.e("MessageDetails", "MobNo: " + mobNo + ", Msg: " + msg);
                        Toast.makeText(context, "A New Message Received", Toast.LENGTH_SHORT).show();

                        // Set the flag to indicate that a message has been received
                        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(KEY_MESSAGE_RECEIVED, true);
                        editor.apply();
                    }
                }
            }
        }
    }
}
