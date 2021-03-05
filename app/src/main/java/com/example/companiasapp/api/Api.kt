package com.example.companiasapp.api

import com.example.companiasapp.api.EndPointService.TOKEN_ACCES
import com.example.companiasapp.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST(TOKEN_ACCES)
    fun userLogin(
        @Field("username") email:String,
        @Field("password") password: String,
        @Field("grant_type") grant_type: String
    ): Call<LoginResponse>
}