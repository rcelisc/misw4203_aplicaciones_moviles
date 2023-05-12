package com.grupo15.vinilos.data.repository.album

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.model.Album
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumDataSource,
    private val localCacheDataSource: AlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbums(): Result<List<Album>> {
        return remoteDataSource.getAlbums()
    }

    override suspend fun getAlbum(id: Int): Result<Album?> {
        val localCollector = localCacheDataSource.getAlbum(id)
        return if (localCollector.getOrNull() != null) {
            localCollector
        } else {
            val remoteCollector = remoteDataSource.getAlbum(id)
            remoteCollector.getOrNull()?.let { localCacheDataSource.saveAlbum(it) }
            return remoteCollector
        }
    }

}
