package com.grupo15.vinilos.data.model

import com.squareup.moshi.Json

data class Collector(
    @Json(name = "name")
    val name: String,
    @Json(name = "telephone")
    val telephone: Int,
    @Json(name = "email")
    val email: String
)
