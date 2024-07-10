package com.piyush.a22_notification_demo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE = 100;
    private static final String CHANNEL_ID = "My_Channel";
    private static final int NOTIFICATION_ID = 100;
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check and request notification permission for Android 13 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
            } else {
                showNotification();
            }
        } else {
            showNotification();
        }
    }

    private void showNotification() {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.new_icon, null);
        Bitmap largeIcon = null;

        if (drawable instanceof BitmapDrawable) {
            largeIcon = ((BitmapDrawable) drawable).getBitmap();
        }

        Intent iNotify = new Intent(getApplicationContext(), MainActivity.class);
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(this, REQ_CODE, iNotify, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                .addLine("A")
                .addLine("B")
                .addLine("C")
                .addLine("D")
                .addLine("E")
                .addLine("F")
                .addLine("G")
                .addLine("H")
                .addLine("I")
                .addLine("J")
                .addLine("K")
                .setBigContentTitle("Full Message")
                .setSummaryText("Message From Piyush");

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (nm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH);
                nm.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.new_icon)
                    .setContentText("New Message")
                    .setSubText("New Message from Piyush")
                    .setContentIntent(pi)
                    .setStyle(inboxStyle)
                    .setOngoing(true)  // Set as ongoing to prevent dismissal
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            Notification notification = builder.build();

            nm.notify(NOTIFICATION_ID, notification);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNotification();
            }
        }
    }
}
