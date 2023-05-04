package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.presentation.albums.getFakeAlbums
import com.grupo15.vinilos.presentation.collectors.getFakeCollector
import com.grupo15.vinilos.presentation.collectors.getFakeCollectors
import com.grupo15.vinilos.presentation.performers.getFakePerformer
import com.grupo15.vinilos.presentation.performers.getFakePerformers
import retrofit2.Response

class FakeVinilosApi : VinilosApi {
    override suspend fun getAlbums(): Response<List<Album>> {
        return Response.success(getFakeAlbums())
    }

    override suspend fun getPerformers(): Response<List<Performer>> {
        return Response.success(getFakePerformers())
    }

    override suspend fun getCollectors(): Response<List<Collector>> {
        return Response.success(getFakeCollectors())
    }

    override suspend fun getCollector(id: String): Response<Collector> {
        return Response.success(getFakeCollector(1))
    }

    override suspend fun getPerformer(id: String): Response<Performer> {
        return Response.success(getFakePerformer(1))
    }
}
