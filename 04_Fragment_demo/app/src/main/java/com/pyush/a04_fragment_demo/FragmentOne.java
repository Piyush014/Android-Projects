package com.pyush.a04_fragment_demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflating the layout of the fragment
        // and returning the view component
        return inflater.inflate(R.layout.fragment_one, container, false);
    }
}
