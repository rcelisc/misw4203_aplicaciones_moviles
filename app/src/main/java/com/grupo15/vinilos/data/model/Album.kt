package com.grupo15.vinilos.data.model

import java.util.Date

data class Album(
    val name: String,
    val cover: String,
    val description: String,
    val releaseDate: Date,
    val genre: String,
    val recordLabel: String
)
