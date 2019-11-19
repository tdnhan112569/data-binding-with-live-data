package com.dragon.mvvm_databinding_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var loginScreen: LoginScreen

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginScreen = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        loginScreen.viewModel = viewModel
        addObserver()
    }

    private fun addObserver() {
        viewModel.status.observe(this, Observer {
            it?.let {
                if (!it) {Toast.makeText(this,"Login fail", Toast.LENGTH_SHORT).show()}
                else {
                    Toast.makeText(this,"Login success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }
}
