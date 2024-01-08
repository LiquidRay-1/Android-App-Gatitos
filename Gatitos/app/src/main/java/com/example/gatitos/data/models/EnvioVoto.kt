package com.example.gatitos.data.models


import com.squareup.moshi.Json

data class EnvioVoto(
    @Json(name = "image_id")
    val imageId: String?,
    @Json(name = "sub_id")
    val subId: String?,
    @Json(name = "value")
    val value: Int?
)