package com.piyush.a32_viewmodel_demo;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private int number = 0;

    public void addNumber() {
        number++;
    }

    public int getNumber() {
        return number;
    }
}
