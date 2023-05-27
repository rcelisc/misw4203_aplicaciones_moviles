package com.grupo15.vinilos.data.datasource.album

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track
import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import javax.inject.Inject

class RemoteAlbumDataSourceImpl @Inject constructor(
    private val vinilosServiceAdapter: VinilosServiceAdapter
) : AlbumDataSource {

    override suspend fun getAlbums(): Result<List<Album>> {
        return vinilosServiceAdapter.getAlbums()
    }

    override suspend fun createAlbum(album: Album):Result<Album> {
        return vinilosServiceAdapter.createAlbum(album)
    }

    override suspend fun setTrackToAlbum(id: Int, track: Track): Result<SetTrackResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbum(id: Int):Result<Album>{
        return vinilosServiceAdapter.getAlbum(id)
    }

}
