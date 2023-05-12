package com.grupo15.vinilos.di

import com.grupo15.vinilos.data.cache.VinilosCacheAdapter
import com.grupo15.vinilos.data.cache.VinilosCacheAdapterImpl
import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.datasource.album.LocalCacheAlbumDataSourceImpl
import com.grupo15.vinilos.data.datasource.collector.CollectorDataSource
import com.grupo15.vinilos.data.datasource.collector.LocalCacheCollectorDataSourceImpl
import com.grupo15.vinilos.data.datasource.performer.LocalCachePerformerDataSourceImpl
import com.grupo15.vinilos.data.datasource.performer.PerformerDataSource
import com.grupo15.vinilos.data.network.VinilosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideCacheAdapter(apiService: VinilosApi): VinilosCacheAdapter = VinilosCacheAdapterImpl()

    @Singleton
    @Provides
    @Named("cache")
    fun provideCacheAlbumDataSource(cacheAdapter: VinilosCacheAdapter): AlbumDataSource =
        LocalCacheAlbumDataSourceImpl(cacheAdapter)

    @Singleton
    @Provides
    @Named("cache")
    fun provideCachePerformerDataSource(cacheAdapter: VinilosCacheAdapter): PerformerDataSource =
        LocalCachePerformerDataSourceImpl(cacheAdapter)

    @Singleton
    @Provides
    @Named("cache")
    fun provideCacheCollectorDataSource(cacheAdapter: VinilosCacheAdapter): CollectorDataSource =
        LocalCacheCollectorDataSourceImpl(cacheAdapter)

}
