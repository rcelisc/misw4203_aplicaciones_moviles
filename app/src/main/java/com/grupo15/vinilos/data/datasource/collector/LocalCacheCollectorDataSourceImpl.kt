package com.grupo15.vinilos.data.datasource.collector

import com.grupo15.vinilos.data.cache.VinilosCacheAdapter
import com.grupo15.vinilos.data.model.Collector
import javax.inject.Inject

class LocalCacheCollectorDataSourceImpl @Inject constructor(
    private val vinilosCacheAdapter: VinilosCacheAdapter
) : CollectorDataSource {

    override suspend fun getCollectors(): Result<List<Collector>> {
        return Result.success(vinilosCacheAdapter.getCollectors())
    }

    override suspend fun getCollector(id: Int): Result<Collector?> {
        return Result.success(vinilosCacheAdapter.getCollector(id))
    }

    override suspend fun saveCollector(collector: Collector) {
        return vinilosCacheAdapter.saveCollector(collector)
    }

}
