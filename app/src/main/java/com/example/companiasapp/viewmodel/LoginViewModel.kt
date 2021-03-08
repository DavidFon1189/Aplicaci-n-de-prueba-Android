package com.example.companiasapp.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.companiasapp.InterfaceListener
import com.example.companiasapp.repository.LoginDetailRepository
import com.example.companiasapp.api.RetrofitClient

class LoginViewModel : ViewModel(){

    var user: String = ""
    var password: String = ""

    var listenerInterface: InterfaceListener? = null

    fun onClickButtonLogin(view: View){
        if (user.isEmpty() || password.isEmpty()){
            listenerInterface?.error("Usario/Contraseña incorrectos")
            return
        }
        listenerInterface?.load()
        val loginResponse = LoginDetailRepository(RetrofitClient()).loginDetail(user, password)
        listenerInterface?.succes(loginResponse)
    }
}