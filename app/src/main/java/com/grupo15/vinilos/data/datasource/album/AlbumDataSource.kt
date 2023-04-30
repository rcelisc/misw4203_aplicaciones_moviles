package com.grupo15.vinilos.data.datasource.album

import com.grupo15.vinilos.data.model.Album

interface AlbumDataSource {

    suspend fun getAlbums(): Result<List<Album>>

}
