package com.example.companiasapp.api

import android.util.Base64
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitClient() {
    private val AUTH =
        "Basic " + "Wm1Ga0xXTXlZeTF3YjNKMFlXdz06TWpoa04yUTNNbUppWVRWbVpHTTBObVl4Wmpka1lXSmpZbVEyTmpBMVpEVXpaVFZoT1dNMVpHVTROakF4TldVeE9EWmtaV0ZpTnpNd1lUUm1ZelV5WWc9PQ=="
    var userName = ""
    var password = ""
    private val BASE_URL = "https://uat.firmaautografa.com/"
    private val API_TIMER = 60

    fun provideAPIService(user: String, pass: String): Api? {
        userName = user
        password = pass
        return provideRetrofit(BASE_URL).create(Api::class.java)
    }

    private fun provideRetrofit(baseUrl: String): Retrofit {
        val headerAuthorizationInterceptor: Interceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
                val body = RequestBody.create(mediaType, "username="+userName+"&password="+password+"&grant_type=password")
                var request: Request = chain.request()
                val headers: Headers =
                    request.headers.newBuilder().add("Authorization", AUTH).build()
                request = request.newBuilder().headers(headers).post(body).build()
                return chain.proceed(request)
            }
        }
        val builder = Retrofit.Builder()
        val httpClient = OkHttpClient.Builder()
        val clientTimer =
            httpClient.connectTimeout(API_TIMER.toLong(), TimeUnit.SECONDS)
                .readTimeout(API_TIMER.toLong(), TimeUnit.SECONDS)
                .writeTimeout(API_TIMER.toLong(), TimeUnit.SECONDS)
                .addInterceptor(headerAuthorizationInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        return builder.client(clientTimer)
            .client(clientTimer)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}