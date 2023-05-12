package com.grupo15.vinilos.data.repository.album

import com.grupo15.vinilos.data.model.Album

interface AlbumRepository {

    suspend fun getAlbums(): Result<List<Album>>

    suspend fun getAlbum(id: Int): Result<Album>

}
