package com.grupo15.vinilos.data.repository.collector

import com.grupo15.vinilos.data.datasource.collector.CollectorDataSource
import com.grupo15.vinilos.data.model.Collector
import javax.inject.Inject

class CollectorRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectorDataSource,
    private val localCacheDataSource: CollectorDataSource
) : CollectorRepository {

    override suspend fun getCollectors(): Result<List<Collector>> {
        return remoteDataSource.getCollectors()
    }

    override suspend fun getCollector(id: Int): Result<Collector?> {
        val localCollector = localCacheDataSource.getCollector(id)
        return if (localCollector.getOrNull() != null) {
            localCollector
        } else {
            val remoteCollector = remoteDataSource.getCollector(id)
            remoteCollector.getOrNull()?.let { localCacheDataSource.saveCollector(it) }
            return remoteCollector
        }
    }

}
