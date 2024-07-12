package com.piyush.a01_viewmodel_demo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*  // For view binding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // Initialize the TextView with the initial number
        textView.text = viewModel.number.toString()

        button.setOnClickListener {
            viewModel.addNumber()
            textView.text = viewModel.number.toString()
        }
    }
}
