package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track
import retrofit2.http.Body

interface VinilosServiceAdapter {

    suspend fun getAlbums(): Result<List<Album>>

    suspend fun getPerformers(): Result<List<Performer>>

    suspend fun getCollectors(): Result<List<Collector>>

    suspend fun getCollector(id: Int): Result<Collector>
    suspend fun getAlbum(id: Int): Result<Album>
    suspend fun createAlbum(album: Album):Result<Album>
    suspend fun getPerformer(id: Int): Result<Performer>
    suspend fun setTrackToAlbum(id: Int, track: Track): Result<SetTrackResponse>
}
