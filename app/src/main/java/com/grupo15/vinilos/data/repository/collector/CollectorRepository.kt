package com.grupo15.vinilos.data.repository.collector

import com.grupo15.vinilos.data.model.Collector

interface CollectorRepository {

    suspend fun getCollectors(): Result<List<Collector>>

    suspend fun getCollector(id: String): Result<Collector>

}
