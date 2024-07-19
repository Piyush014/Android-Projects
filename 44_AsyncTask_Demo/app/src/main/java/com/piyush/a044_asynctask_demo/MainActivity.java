package com.piyush.a044_asynctask_demo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button buttonStart, buttonStop;
    private TextView textViewthreadCount;
    private Handler handler;
    int count = 0;
    private MyAsyncTask myAsyncTask;

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
        textViewthreadCount = findViewById(R.id.textViewThreadCount);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        handler = new Handler(getMainLooper());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.buttonThreadStarter) {
            mStopLoop = true;
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mStopLoop) {
                        try {
                            Thread.sleep(1000);
                            count++;
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            Log.i(TAG, e.getMessage());
                        }
                        Log.i(TAG, "Thread id in while loop: " + Thread.currentThread().getId() + ", Count : " + count);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textViewthreadCount.setText("" + count);
                            }
                        });
                    }
                }
            }).start(); */
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(count);
        } else if (id == R.id.buttonStopthread) {
            myAsyncTask.cancel(true);

            // Reset the TextView to its original text after 10 seconds
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewthreadCount.setText(ORIGINAL_TEXT);
                }
            }, 10000); // 10000 milliseconds = 10 seconds
        }

    }
    public class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        private int customCounter;
        protected void onPreExecute(){
            super.onPreExecute();
            customCounter = 0;
        }
        @Override
        protected Integer doInBackground(Integer... integers) {
            customCounter = integers[0];
            while (mStopLoop) {
                try {
                    Thread.sleep(1000);
                    customCounter++;
                    publishProgress(customCounter);
                } catch (InterruptedException e) {
                    Log.i(TAG, e.getMessage());
                }
            }
            return customCounter;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textViewthreadCount.setText("" + integer);
            count = integer;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textViewthreadCount.setText(""+values[0]);
        }
    }
}
