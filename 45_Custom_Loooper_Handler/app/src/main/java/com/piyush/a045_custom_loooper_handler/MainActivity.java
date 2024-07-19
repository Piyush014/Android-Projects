package com.piyush.a045_custom_loooper_handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button buttonStart, buttonStop;
    private TextView textViewThreadCount;
    private Handler handler;
    private int count = 0;
    private LooperThread looperThread;
    private boolean mStopLoop;
    private static final String ORIGINAL_TEXT = "Thread Count";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Thread Id:" + Thread.currentThread().getId());
        buttonStart = findViewById(R.id.buttonThreadStarter);
        buttonStop = findViewById(R.id.buttonStopthread);
        textViewThreadCount = findViewById(R.id.textViewThreadCount);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        handler = new Handler(getMainLooper());

        // Initialize the LooperThread
        looperThread = new LooperThread();
        looperThread.start();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.buttonThreadStarter) {
            mStopLoop = true;
            executeOnCustomLooper();
        } else if (id == R.id.buttonStopthread) {
            mStopLoop = false;

            // Reset the TextView to its original text after 10 seconds
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewThreadCount.setText(ORIGINAL_TEXT);
                }
            }, 10000); // 10000 milliseconds = 10 seconds
        }
    }

    public void executeOnCustomLooper() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mStopLoop) {
                    try {
                        Log.i(TAG, "Thread id of thread that sends message: " + Thread.currentThread().getId());
                        Thread.sleep(1000);
                        count++;
                        Message message = getMessageWithCount("" + count);
                        looperThread.handler.sendMessage(message);
                    } catch (InterruptedException exception) {
                        Log.i(TAG, "Thread interrupted");
                    }
                }
            }
        }).start();
    }

    private Message getMessageWithCount(String count) {
        Message message = new Message();
        message.obj = count;
        return message;
    }

    private class LooperThread extends Thread {
        public Handler handler;

        @Override
        public void run() {
            Looper.prepare();

            handler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    String count = (String) msg.obj;
                    Log.i(TAG, "Thread id of handler: " + Thread.currentThread().getId() + ", Count: " + count);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textViewThreadCount.setText(count);
                        }
                    });
                }
            };

            Looper.loop();
        }
    }
}
