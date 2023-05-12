package com.grupo15.vinilos.data.repository.performer

import com.grupo15.vinilos.data.datasource.performer.PerformerDataSource
import com.grupo15.vinilos.data.model.Performer
import javax.inject.Inject

class PerformerRepositoryImpl @Inject constructor(
    private val remoteDataSource: PerformerDataSource,
    private val localCacheDataSource: PerformerDataSource
) : PerformerRepository {

    override suspend fun getPerformers(): Result<List<Performer>> {
        return remoteDataSource.getPerformers()
    }

    override suspend fun getPerformer(id: Int): Result<Performer?> {
        return remoteDataSource.getPerformer(id)
    }

}
