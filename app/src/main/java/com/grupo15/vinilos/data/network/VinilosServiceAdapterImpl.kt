package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.network.utils.ServiceMapper.toResult
import javax.inject.Inject

class VinilosServiceAdapterImpl @Inject constructor(
    private val vinilosApi: VinilosApi
) : VinilosServiceAdapter {

    override suspend fun getAlbums(): Result<List<Album>> = vinilosApi.getAlbums().toResult()

}
