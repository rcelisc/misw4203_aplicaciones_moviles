package com.grupo15.vinilos.data.datasource.performer

import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import javax.inject.Inject

class RemotePerformerDataSourceImpl @Inject constructor(
    private val vinilosServiceAdapter: VinilosServiceAdapter
) : PerformerDataSource {

    override suspend fun getPerformers(): Result<List<Performer>> {
        return vinilosServiceAdapter.getPerformers()
    }

    override suspend fun getPerformer(id: Int): Result<Performer?> {
        return vinilosServiceAdapter.getPerformer(id)
    }

    override suspend fun savePerformer(performer: Performer) {
        TODO("Not yet implemented")
    }
}
