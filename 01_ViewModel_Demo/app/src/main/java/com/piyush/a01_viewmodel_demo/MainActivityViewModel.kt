package com.piyush.a01_viewmodel_demo

import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    var number = 0
    fun addNumber(){
        number++
    }

    override fun onCleared() {
        super.onCleared()
    }
}