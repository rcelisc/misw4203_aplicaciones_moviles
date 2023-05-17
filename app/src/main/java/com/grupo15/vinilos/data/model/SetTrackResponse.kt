package com.grupo15.vinilos.data.model

import com.squareup.moshi.Json

data class SetTrackResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "duration")
    val duration: String,

    val album: Album
)
