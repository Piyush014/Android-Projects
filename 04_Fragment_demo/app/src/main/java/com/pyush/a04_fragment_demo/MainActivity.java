package com.pyush.a04_fragment_demo;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // method for displaying the appropriate fragment according to the clicked button
    public void selectFragment(View view) {
        Fragment fr;

        // displaying first fragment if button1 is clicked
        if (view == findViewById(R.id.button1)) {
            fr = new FragmentOne();
        } else {
            fr = new FragmentTwo();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_section, fr);
        fragmentTransaction.commit();
    }
}
