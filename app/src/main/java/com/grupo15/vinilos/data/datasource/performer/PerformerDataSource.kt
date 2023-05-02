package com.grupo15.vinilos.data.datasource.performer

import com.grupo15.vinilos.data.model.Performer

interface PerformerDataSource {

    suspend fun getPerformers(): Result<List<Performer>>

    suspend fun getCollectors(id: String): Result<Performer>

    suspend fun getPerformer(id: String): Result<Performer>

}
