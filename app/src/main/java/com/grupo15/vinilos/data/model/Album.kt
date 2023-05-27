package com.grupo15.vinilos.data.model

import com.squareup.moshi.Json
import java.util.Date

data class Album(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "cover")
    val cover: String,
    @Json(name = "releaseDate")
    val releaseDate: Date,
    @Json(name = "description")
    val description: String,
    @Json(name = "genre")
    val genre: String,
    @Json(name = "recordLabel")
    val recordLabel: String?,
    @Json(name = "tracks")
    val tracks: List<Track>?,
    @Json(name = "performers")
    val performers: List<Performer>?,
    @Json(name = "comments")
    val comments: List<Comment>?
)

