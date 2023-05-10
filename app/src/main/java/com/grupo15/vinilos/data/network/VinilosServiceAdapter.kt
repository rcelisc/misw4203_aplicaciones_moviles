package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.Performer

interface VinilosServiceAdapter {

    suspend fun getAlbums(): Result<List<Album>>

    suspend fun getPerformers(): Result<List<Performer>>

    suspend fun getCollectors(): Result<List<Collector>>

    suspend fun getCollector(id: Int): Result<Collector>

    suspend fun getPerformer(id: Int): Result<Performer>
}
