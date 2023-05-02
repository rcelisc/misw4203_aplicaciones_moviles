package com.grupo15.vinilos.data.datasource.collector

import com.grupo15.vinilos.data.model.Collector

interface CollectorDataSource {

    suspend fun getCollectors(): Result<List<Collector>>

    suspend fun getCollectors(id: String): Result<Collector>

}
