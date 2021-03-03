package com.example.companiasapp.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.companiasapp.InterfaceListener
import com.example.companiasapp.LoginViewModel
import com.example.companiasapp.R
import com.example.companiasapp.databinding.MainActivityBinding
import com.example.companiasapp.model.LoginResponse
import com.example.companiasapp.model.LoginResponseError
import com.google.gson.Gson

class MainActivity: AppCompatActivity(), InterfaceListener {

    private lateinit var dataBinding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        dataBinding.loginviewmodel = viewModel
        viewModel.listenerInterface = this
        val prefences = getPreferences(Context.MODE_PRIVATE)
        Toast.makeText(this, "BIENVENIDO", Toast.LENGTH_LONG).show()
    }

    override fun succes(loginResponse: LiveData<String>) {
        val gson = Gson()
        loginResponse.observe(this, Observer {
            val responseSucces = gson.fromJson(it, LoginResponse::class.java)
            val responseError = gson.fromJson(it, LoginResponseError::class.java)
            if(responseSucces.accessToken != "") {
                Toast.makeText(this, responseSucces.accessToken, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, responseError.error, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun error(message: String) {
       Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun load() {

    }

    override fun hideLoad() {

    }
}