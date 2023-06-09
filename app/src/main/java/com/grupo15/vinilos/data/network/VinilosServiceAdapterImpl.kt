package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track
import com.grupo15.vinilos.data.network.utils.ResponseException
import com.grupo15.vinilos.data.network.utils.ServiceMapper.toResult
import javax.inject.Inject

class VinilosServiceAdapterImpl @Inject constructor(
    private val vinilosApi: VinilosApi
) : VinilosServiceAdapter {

    override suspend fun getAlbums(): Result<List<Album>> =
        try {
            vinilosApi.getAlbums().toResult()
        } catch (e: Exception) {
            Result.failure(ResponseException.NoConnectionException())
        }

    override suspend fun getPerformers(): Result<List<Performer>> =
        try {
            vinilosApi.getPerformers().toResult()
        } catch (e: Exception) {
            Result.failure(ResponseException.NoConnectionException())
        }

    override suspend fun getCollectors(): Result<List<Collector>> =
        try {
            vinilosApi.getCollectors().toResult()
        } catch (e: Exception) {
            Result.failure(ResponseException.NoConnectionException())
        }

    override suspend fun getAlbum(id: Int): Result<Album> =
        try {
            vinilosApi.getAlbum(id).toResult()
        } catch (e: Exception) {
            Result.failure(ResponseException.NoConnectionException())
        }

    override suspend fun createAlbum(album: Album): Result<Album> =
        try {
            vinilosApi.createAlbum(album).toResult()
        }catch (e: Exception){
            Result.failure(ResponseException.NoConnectionException())
        }

    override suspend fun getCollector(id: Int): Result<Collector> =
        try {
            vinilosApi.getCollector(id).toResult()
        } catch (e: Exception) {
            Result.failure(ResponseException.NoConnectionException())
        }

    override suspend fun getPerformer(id: Int): Result<Performer> =
        try {
            vinilosApi.getPerformer(id).toResult()
        } catch (e: Exception) {
            Result.failure(ResponseException.NoConnectionException())
        }

    override suspend fun setTrackToAlbum(id: Int, track: Track): Result<SetTrackResponse> =
        try {
            vinilosApi.setTrackToAlbum(id, track).toResult()
        } catch (e: Exception) {
            Result.failure(ResponseException.NoConnectionException())
        }

}
