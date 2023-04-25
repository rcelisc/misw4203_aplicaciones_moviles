package com.grupo15.vinilos.data.datasource.collector

import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import javax.inject.Inject

class CollectorDataSourceImpl @Inject constructor(
    private val vinilosServiceAdapter: VinilosServiceAdapter
) : CollectorDataSource {

    override suspend fun getCollectors(): Result<List<Collector>> {
        return vinilosServiceAdapter.getCollectors()
    }

}
