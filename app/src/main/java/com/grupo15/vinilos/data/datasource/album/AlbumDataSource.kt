package com.grupo15.vinilos.data.datasource.album

import com.grupo15.vinilos.data.model.Album

interface AlbumDataSource {

    fun getAlbums(): List<Album>

}
