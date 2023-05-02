package com.grupo15.vinilos.data.repository.collector

import com.grupo15.vinilos.data.datasource.collector.CollectorDataSource
import com.grupo15.vinilos.data.model.Collector
import javax.inject.Inject

class CollectorRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectorDataSource
) : CollectorRepository {

    override suspend fun getCollectors(): Result<List<Collector>> {
        return remoteDataSource.getCollectors()
    }

    override suspend fun getCollector(id: String): Result<Collector> {
        return remoteDataSource.getCollector(id)
    }

}
