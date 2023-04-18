package com.grupo15.vinilos.data.repository.album

import com.grupo15.vinilos.data.model.Album

interface AlbumRepository {

    fun getAlbums(): List<Album>

}
