package com.example.companiasapp

data class ServiciosList(
    val items: ArrayList<ServiciosData>
)

data class ServiciosData(
    val servicio: String
)

data class CompaniasList(
    val items: ArrayList<CompaniasData>
    )

data class CompaniasData(
    val titulo: String,
    val description: String,
    val owner: Owner
    )

data class Owner(
    var avatar_url: String
    )