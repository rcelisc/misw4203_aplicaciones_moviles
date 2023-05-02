package com.grupo15.vinilos.data.repository.performer

import com.grupo15.vinilos.data.datasource.performer.PerformerDataSource
import com.grupo15.vinilos.data.model.Performer
import javax.inject.Inject

class PerformerRepositoryImpl @Inject constructor(
    private val remoteDataSource: PerformerDataSource
) : PerformerRepository {

    override suspend fun getPerformers(): Result<List<Performer>> {
        return remoteDataSource.getPerformers()
    }

    override suspend fun getPerformer(id: String): Result<Performer> {
        return remoteDataSource.getPerformer(id)
    }

}
