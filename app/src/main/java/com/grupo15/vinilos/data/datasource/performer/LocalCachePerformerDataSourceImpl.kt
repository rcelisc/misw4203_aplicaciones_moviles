package com.grupo15.vinilos.data.datasource.performer

import com.grupo15.vinilos.data.cache.VinilosCacheAdapter
import com.grupo15.vinilos.data.model.Performer
import javax.inject.Inject

class LocalCachePerformerDataSourceImpl @Inject constructor(
    private val vinilosCacheAdapter: VinilosCacheAdapter
) : PerformerDataSource {

    override suspend fun getPerformers(): Result<List<Performer>> {
        return Result.success(vinilosCacheAdapter.getPerformers())
    }

    override suspend fun getPerformer(id: Int): Result<Performer?> {
        return Result.success(vinilosCacheAdapter.getPerformer(id))
    }

    override suspend fun savePerformer(performer: Performer) {
        return vinilosCacheAdapter.savePerformer(performer)
    }

}
