package com.example.yourbabytestproject

import com.squareup.moshi.Json

data class EventInfo(val id:Int,
                     @Json(name = "full_description")val fullDescription: String,
                     @Json(name = "video_url")val videoUrl: String)