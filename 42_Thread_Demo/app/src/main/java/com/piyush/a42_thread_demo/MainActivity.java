package com.piyush.a42_thread_demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStart, buttonStop;
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Thread Id: " + Thread.currentThread().getId());
        buttonStart = findViewById(R.id.button);
        buttonStop = findViewById(R.id.buttonThreadStop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            mStopLoop = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mStopLoop) {
                        Log.i(TAG, "Thread id in while loop: " + Thread.currentThread().getId());
                        try {
                            Thread.sleep(1000); // Adding a delay for readability
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }).start();
        } else if (view.getId() == R.id.buttonThreadStop) {
            mStopLoop = false;
        }
    }
}
