package com.piyush.a04_okhttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client;
    TextView textView;
    EditText etName, etJob;

    String getUrl = "https://jsonplaceholder.typicode.com/posts/1";
    String postUrl = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        textView = findViewById(R.id.textData);
        etName = findViewById(R.id.etGivenName);
        etJob = findViewById(R.id.etGivenJob);
        Button buttonGet = findViewById(R.id.btnGET);
        Button buttonPost = findViewById(R.id.btnPOST);

        setupNetworkCallback();

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    get();
                } else {
                    showNetworkError();
                }
            }
        });

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    post();
                } else {
                    showNetworkError();
                }
            }
        });
    }

    private void setupNetworkCallback() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkRequest networkRequest = new NetworkRequest.Builder().build();
            connectivityManager.registerNetworkCallback(networkRequest, new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Network Available", Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onLost(Network network) {
                    runOnUiThread(() -> {
                        Toast.makeText(MainActivity.this, "Network Lost", Toast.LENGTH_SHORT).show();
                        showNetworkError();
                    });
                }
            });
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            Network network = connectivityManager.getActiveNetwork();
            return network != null;
        }
        return false;
    }

    private void showNetworkError() {
        Toast.makeText(this, "Network not available. Please check your connection.", Toast.LENGTH_SHORT).show();
    }

    public void get() {
        Request request = new Request.Builder().url(getUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "GET request failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(() -> {
                    try {
                        textView.setText(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error reading response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void post() {
        String name = etName.getText().toString();
        String job = etJob.getText().toString();

        if (name.isEmpty() || job.isEmpty()) {
            Toast.makeText(this, "Please enter both name and job.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .add("job", job)
                .build();

        Request request = new Request.Builder().url(postUrl).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "POST request failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(() -> {
                    try {
                        textView.setText(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error reading response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
