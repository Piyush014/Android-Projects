package com.piyush.a47_retrofit_demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.piyush.a47_retrofit_demo.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AlbumService albumService = RetrofitInstance.getRetrofitInstance().create(AlbumService.class);

        LiveData<Response<List<Album>>> responseLiveData = new LiveData<Response<List<Album>>>() {
            @Override
            protected void onActive() {
                super.onActive();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response<List<Album>> response = albumService.getAlbums().execute();
                            postValue(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        };

        responseLiveData.observe(this, new Observer<Response<List<Album>>>() {
            @Override
            public void onChanged(Response<List<Album>> response) {
                List<Album> albumList = response.body();
                if (albumList != null) {
                    for (Album albumItem : albumList) {
                        String albumTitle = "Album Title: " + albumItem.getTitle() + " \n";
                        binding.titleTextView.append(albumTitle);
                    }
                }
            }
        });
    }
}
