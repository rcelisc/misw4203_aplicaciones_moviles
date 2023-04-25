package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Performer
import retrofit2.Response
import retrofit2.http.GET

interface VinilosApi {

    @GET("/albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("/musicians")
    suspend fun getPerformers(): Response<List<Performer>>

}
