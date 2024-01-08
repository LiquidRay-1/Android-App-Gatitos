package com.example.gatitos.data.models


import com.squareup.moshi.Json

data class Image(
    @field:Json(name = "height")
    val height: Int?,
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "url")
    val url: String?,
    @field:Json(name = "width")
    val width: Int?
)