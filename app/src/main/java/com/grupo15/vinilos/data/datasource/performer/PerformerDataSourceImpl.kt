package com.grupo15.vinilos.data.datasource.performer

import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import javax.inject.Inject

class PerformerDataSourceImpl @Inject constructor(
    private val vinilosServiceAdapter: VinilosServiceAdapter
) : PerformerDataSource {

    override suspend fun getPerformers(): Result<List<Performer>> {
        return vinilosServiceAdapter.getPerformers()
    }

}
