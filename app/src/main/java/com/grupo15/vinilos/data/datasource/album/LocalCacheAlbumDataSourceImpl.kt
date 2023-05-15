package com.grupo15.vinilos.data.datasource.album

import com.grupo15.vinilos.data.cache.VinilosCacheAdapter
import com.grupo15.vinilos.data.model.Album
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

    override suspend fun saveAlbum(album: Album) {
        return vinilosCacheAdapter.saveAlbum(album)
    }

}
