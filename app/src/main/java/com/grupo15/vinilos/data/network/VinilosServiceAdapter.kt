package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album

interface VinilosServiceAdapter {

    suspend fun getAlbums(): Result<List<Album>>

}
