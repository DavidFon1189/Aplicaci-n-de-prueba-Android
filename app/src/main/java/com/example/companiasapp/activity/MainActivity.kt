package com.example.companiasapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.companiasapp.InterfaceListener
import com.example.companiasapp.viewmodel.LoginViewModel
import com.example.companiasapp.R
import com.example.companiasapp.utils.DialogManager
import com.example.companiasapp.databinding.MainActivityBinding
import com.example.companiasapp.model.LoginResponse

class MainActivity: AppCompatActivity(), InterfaceListener {

    private lateinit var dataBinding: MainActivityBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        dataBinding.loginviewmodel = viewModel
        viewModel.listenerInterface = this
    }

    override fun succes(loginResponse: LiveData<LoginResponse>) {
        loginResponse.observe(this, Observer {
            preferences = getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE)
            if (it != null) {
                val editor: SharedPreferences.Editor = preferences.edit()
                editor.putString("access_token", it.accessToken)
                editor.putString("token_type", it.tokenType)
                editor.putString("refresh_token", it.refreshToken)
                editor.putInt("expires_in", it.expiresIn)
                editor.putString("scope", it.scope)
                editor.putString("expires_in", it.jti)
                editor.apply()
                startActivity(Intent(this, RecargasActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Bad credentials", Toast.LENGTH_SHORT).show()
            }
            DialogManager.progressBar(this).dismiss()
        })
    }

    override fun error(message: String) {
       Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        DialogManager.progressBar(this).dismiss()
    }

    override fun load() {
        DialogManager.progressBar(this)
    }
}