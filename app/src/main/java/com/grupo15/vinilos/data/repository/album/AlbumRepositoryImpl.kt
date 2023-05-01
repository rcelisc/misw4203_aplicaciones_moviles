package com.grupo15.vinilos.data.repository.album

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.model.Album
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbums(): Result<List<Album>> {
        return remoteDataSource.getAlbums()
    }

    override suspend fun getAlbum(id: String): Result<Album> {
        TODO("Not yet implemented")
    }

}
