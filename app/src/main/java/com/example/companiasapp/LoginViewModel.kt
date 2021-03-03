package com.example.companiasapp

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.companiasapp.api.RetrofitClient

class LoginViewModel : ViewModel(){

    var user: String = ""
    var password: String = ""

    var listenerInterface: InterfaceListener? = null

    fun onClickButtonLogin(view: View){
        listenerInterface?.load()
        if (user.isEmpty() || password.isEmpty()){
            listenerInterface?.error("Usario/Contraseña incorrectos")
            return
        }

        val loginResponse = LoginDetailRepository(RetrofitClient()).loginDetail(user, password)
        listenerInterface?.succes(loginResponse)
    }
}