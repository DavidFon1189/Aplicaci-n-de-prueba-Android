package com.example.companiasapp.api

import com.example.companiasapp.api.EndPointService.TOKEN_ACCES
import com.example.companiasapp.model.LoginRequest
import com.example.companiasapp.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST(TOKEN_ACCES)
    fun userLogin(): Call<LoginResponse>

    @FormUrlEncoded
    @POST(TOKEN_ACCES)
    fun userLoginR(
        @Field("username") email:String,
        @Field("password") password: String,
        @Field("grant_type") grant_type: String
    ): Call<LoginResponse>
}

