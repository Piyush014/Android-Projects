package com.pyush.a05_view_pager;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);

        // Call getTabs() method to set up ViewPager and TabLayout
        getTabs();
    }

    private void getTabs() {
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addFragment(ChatFragment.getInstance(), "CHATS");
        viewPagerAdapter.addFragment(StatusFragment.getInstance(), "STATUS");
        viewPagerAdapter.addFragment(CallsFragment.getInstance(), "CALLS");

        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(viewPagerAdapter.getPageTitle(position))).attach();
    }
}
