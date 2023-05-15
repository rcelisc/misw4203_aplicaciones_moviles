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
        val localPerformer = localCacheDataSource.getPerformer(id)
        return if (localPerformer.getOrNull() != null) {
            localPerformer
        } else {
            val remotePerformer = remoteDataSource.getPerformer(id)
            remotePerformer.getOrNull()?.let { localCacheDataSource.savePerformer(it) }
            return remotePerformer
        }
        return remoteDataSource.getPerformer(id)
    }
}
