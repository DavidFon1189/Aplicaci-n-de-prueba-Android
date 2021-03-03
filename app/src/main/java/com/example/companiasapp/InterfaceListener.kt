package com.example.companiasapp

import androidx.lifecycle.LiveData
import com.example.companiasapp.model.LoginResponse

interface InterfaceListener {
    fun succes(loginResponse: LiveData<String>)
    fun error(message: String)
    fun load()
    fun hideLoad()
}