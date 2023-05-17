package com.grupo15.vinilos.data.datasource.album

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track

interface AlbumDataSource {

    suspend fun getAlbums(): Result<List<Album>>

    suspend fun getAlbum(id: Int): Result<Album?>

    suspend fun saveAlbum(album: Album)

    suspend fun setTrackToAlbum(id: Int, track: Track): Result <SetTrackResponse>

}
