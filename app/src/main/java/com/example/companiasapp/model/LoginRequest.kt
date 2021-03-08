package com.example.companiasapp.model

import retrofit2.http.Field

class LoginRequest {
    var username = ""
    var password = ""
    var grant_type = "password"
}