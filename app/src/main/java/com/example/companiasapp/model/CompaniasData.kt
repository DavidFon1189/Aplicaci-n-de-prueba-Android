package com.example.companiasapp.model

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
    val compania: String,
    val description: String,
    internal val img: String
)

data class MontoRecargaList(
    val items: ArrayList<String>
)