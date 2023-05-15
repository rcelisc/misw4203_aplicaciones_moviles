package com.grupo15.vinilos.data.datasource.collector

import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import javax.inject.Inject

class RemoteCollectorDataSourceImpl @Inject constructor(
    private val vinilosServiceAdapter: VinilosServiceAdapter
) : CollectorDataSource {

    override suspend fun getCollectors(): Result<List<Collector>> {
        return vinilosServiceAdapter.getCollectors()
    }

    override suspend fun getCollector(id: Int): Result<Collector> {
        return vinilosServiceAdapter.getCollector(id)
    }

    override suspend fun saveCollector(collector: Collector) {
        TODO("Not yet implemented")
    }


}
