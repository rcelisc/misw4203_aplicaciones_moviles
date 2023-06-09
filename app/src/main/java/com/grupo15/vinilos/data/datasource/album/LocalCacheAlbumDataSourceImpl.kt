package com.grupo15.vinilos.data.datasource.album

import com.grupo15.vinilos.data.cache.VinilosCacheAdapter
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track
import javax.inject.Inject

class LocalCacheAlbumDataSourceImpl @Inject constructor(
    private val vinilosCacheAdapter: VinilosCacheAdapter
) : AlbumDataSource {

    override suspend fun getAlbums(): Result<List<Album>> {
        return Result.success(vinilosCacheAdapter.getAlbums())
    }

    override suspend fun getAlbum(id: Int): Result<Album?> {
        return Result.success(vinilosCacheAdapter.getAlbum(id))
    }

    override suspend fun createAlbum(album: Album): Result<Album> {
        return Result.success(vinilosCacheAdapter.createAlbum(album))
    }

    override suspend fun setTrackToAlbum(id: Int, track: Track): Result<SetTrackResponse> {
        TODO("Not yet implemented")
    }

}
