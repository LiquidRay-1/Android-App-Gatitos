package com.example.gatitos.data.models


import com.squareup.moshi.Json

data class Weight(
    @field:Json(name = "imperial")
    val imperial: String?,
    @field:Json(name = "metric")
    val metric: String?
)