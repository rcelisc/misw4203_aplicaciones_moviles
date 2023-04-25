package com.grupo15.vinilos.data.model

import com.squareup.moshi.Json

data class Collector(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "telephone")
    val telephone: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "comments")
    val comments: List<Comment>?,
    @Json(name = "favoritePerformers")
    val favoritePerformers: List<Performer>?,
    @Json(name = "collectorAlbums")
    val collectorAlbums: List<CollectorAlbum>?
)

data class CollectorAlbum(
    @Json(name = "id")
    val id: Int,
    @Json(name = "price")
    val price: Int,
    @Json(name = "status")
    val status: String
)
