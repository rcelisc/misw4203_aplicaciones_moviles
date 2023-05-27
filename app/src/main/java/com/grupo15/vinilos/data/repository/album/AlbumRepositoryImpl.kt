package com.grupo15.vinilos.data.repository.album

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumDataSource,
    private val localCacheDataSource: AlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbums(): Result<List<Album>> {
        return remoteDataSource.getAlbums()
    }

    override suspend fun setTrackToAlbum(id: Int, track: Track): Result<SetTrackResponse> {
        return remoteDataSource.setTrackToAlbum(id, track)
    }

    override suspend fun createAlbum(album: Album): Result<Album> {
        TODO("Not yet implemented")
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
