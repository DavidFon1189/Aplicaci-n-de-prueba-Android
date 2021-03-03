package com.example.companiasapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.companiasapp.api.RetrofitClient
import com.example.companiasapp.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginDetailRepository constructor(retrofitClient: RetrofitClient ){

    private var retrofitClientClient: RetrofitClient? = null

    init {
        retrofitClientClient = retrofitClient
    }

    fun loginDetail(user: String, password: String): LiveData<String>{
        val loginResponse = MutableLiveData<String>()
        retrofitClientClient?.provideAPIService()?.userLogin(user, password, "password")?.enqueue(object:
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body().toString()
                } else {
                    loginResponse.value = response.errorBody()?.string()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginResponse.value = t.message
            }

        })
        return loginResponse
    }
}