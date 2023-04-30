package com.grupo15.vinilos.data.datasource.performer

import com.grupo15.vinilos.data.model.Performer

interface PerformerDataSource {

    suspend fun getPerformers(): Result<List<Performer>>

}
