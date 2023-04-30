package com.grupo15.vinilos.data.model

import com.squareup.moshi.Json

data class Comment(
    @Json(name = "id")
    val id: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "rating")
    val rating: Int
)
