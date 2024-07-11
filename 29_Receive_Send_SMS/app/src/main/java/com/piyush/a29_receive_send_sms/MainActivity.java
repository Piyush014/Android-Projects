package com.piyush.a29_receive_send_sms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String PREFS_NAME = "SMSReceiverPrefs";
    private static final String KEY_MESSAGE_SENT = "message_sent";

    private EditText editTextMobileNumber;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        setContentView(R.layout.activity_main);
        setTitle("Broadcast Receiver SMS Demo App");

        editTextMobileNumber = findViewById(R.id.editText_mobile_number);
        editTextMessage = findViewById(R.id.editText_message);
        Button sendSmsButton = findViewById(R.id.button_send_sms);

        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });

        requestSmsPermissions();
    }

    private void requestSmsPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_CODE);
        }
    }

    private void sendSMS() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // Remove the following line to allow sending SMS anytime
        boolean messageSent = prefs.getBoolean(KEY_MESSAGE_SENT, false);

        // Remove this check to send SMS anytime
    /*if (messageSent) {
        Toast.makeText(this, "Message has already been sent.", Toast.LENGTH_SHORT).show();
        return;
    }*/

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            try {
                String phoneNumber = editTextMobileNumber.getText().toString();
                String message = editTextMessage.getText().toString();

                if (phoneNumber.isEmpty() || message.isEmpty()) {
                    Toast.makeText(this, "Please enter a valid phone number and message.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                Toast.makeText(this, "SMS sent.", Toast.LENGTH_SHORT).show();

                // Clear the input fields
                editTextMobileNumber.setText("");
                editTextMessage.setText("");

                // Reset the flag after sending the SMS
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(KEY_MESSAGE_SENT, false); // Set to false to allow sending again
                editor.apply();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to send SMS.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "SMS permission not granted.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                Toast.makeText(this, "Permissions granted.", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied
                Toast.makeText(this, "Permissions are required for the app to function", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
