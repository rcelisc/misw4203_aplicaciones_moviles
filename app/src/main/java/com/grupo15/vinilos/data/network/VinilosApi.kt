package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VinilosApi {

    @GET("/albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("/musicians")
    suspend fun getPerformers(): Response<List<Performer>>

    @GET("/collectors")
    suspend fun getCollectors(): Response<List<Collector>>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id")id: Int): Response<Album>

    @GET("/collectors/{id}")
    suspend fun getCollector(@Path("id") id: Int): Response<Collector>

    @GET("/musicians/{id}")
    suspend fun getPerformer(@Path("id") id: Int): Response<Performer>

    @POST("/albums")
    suspend fun createAlbum(@Body album: Album):Response <Album>

    @POST("/albums/{id}/tracks")
    suspend fun setTrackToAlbum(@Path("id") id: Int, @Body track: Track): Response<SetTrackResponse>

}
