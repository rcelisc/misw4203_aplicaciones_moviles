package com.grupo15.vinilos.data.model

import com.squareup.moshi.Json
import java.util.Date

data class Performer(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "birthDate")
    val birthDate: Date?,
    @Json(name = "creationDate")
    val creationDate: Date?
)
