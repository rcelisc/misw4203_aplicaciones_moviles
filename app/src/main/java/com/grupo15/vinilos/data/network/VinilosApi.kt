package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album
import retrofit2.Response
import retrofit2.http.GET

interface VinilosApi {

    @GET("/albums")
    suspend fun getAlbums(): Response<List<Album>>

}
