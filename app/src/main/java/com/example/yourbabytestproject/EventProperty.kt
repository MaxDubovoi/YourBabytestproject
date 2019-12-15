package com.example.yourbabytestproject

import com.squareup.moshi.Json

data class EventProperty(
    val id: Int,
    @Json(name = "image_Id") val imageId: Int,
    val title: String,
    @Json(name = "short_description") val shortDescription: String
)