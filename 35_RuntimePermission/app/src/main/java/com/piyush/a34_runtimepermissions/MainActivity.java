package com.piyush.a34_runtimepermissions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_SMS;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    Button btnRequest;
    private static final int REQ_CODE = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnRequest = findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()){
                    Toast.makeText(MainActivity.this, "Permission Already Granted", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{ACCESS_FINE_LOCATION, READ_SMS},REQ_CODE);
                }
            }
        });
    }

    public boolean checkPermission(){
        int resL = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);
        int resMR = ActivityCompat.checkSelfPermission(this, READ_SMS);
        return resL == PackageManager.PERMISSION_GRANTED && resMR == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == REQ_CODE) {
            if (grantResults.length > 0) {
                int location = grantResults[0];
                int message = grantResults[1];

                boolean checkLoc = location == PackageManager.PERMISSION_GRANTED;
                boolean checkM = message == PackageManager.PERMISSION_GRANTED;
                if (checkLoc && checkM) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}