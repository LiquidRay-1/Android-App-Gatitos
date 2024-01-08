package com.example.gatitos.data.models


import com.squareup.moshi.Json

data class EnvioGetVoto(
    @Json(name = "country_code")
    val countryCode: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: Image?,
    @Json(name = "image_id")
    val imageId: String?,
    @Json(name = "sub_id")
    val subId: String?,
    @Json(name = "value")
    val value: Int?
)