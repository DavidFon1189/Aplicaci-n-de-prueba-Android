package com.example.companiasapp

import androidx.lifecycle.LiveData
import com.example.companiasapp.model.LoginResponse

interface InterfaceListener {
    fun succes(loginResponse: LiveData<LoginResponse>)
    fun error(message: String)
    fun load()
}