package com.example.companiasapp.model

import com.google.gson.annotations.SerializedName

data class LoginResponseError(
    @SerializedName("success")
    var success: String,
    @SerializedName("error")
    var error: String
)