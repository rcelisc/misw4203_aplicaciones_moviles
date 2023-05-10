package com.grupo15.vinilos.di

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.datasource.collector.CollectorDataSource
import com.grupo15.vinilos.data.datasource.performer.PerformerDataSource
import com.grupo15.vinilos.data.repository.album.AlbumRepository
import com.grupo15.vinilos.data.repository.album.AlbumRepositoryImpl
import com.grupo15.vinilos.data.repository.collector.CollectorRepository
import com.grupo15.vinilos.data.repository.collector.CollectorRepositoryImpl
import com.grupo15.vinilos.data.repository.performer.PerformerRepository
import com.grupo15.vinilos.data.repository.performer.PerformerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAlbumRepository(
        @Named("remote") remoteDataSource: AlbumDataSource,
        @Named("cache")  localCacheDataSource: AlbumDataSource
    ): AlbumRepository =
        AlbumRepositoryImpl(remoteDataSource, localCacheDataSource)

    @Singleton
    @Provides
    fun providePerformerRepository(
        @Named("remote")  remoteDataSource: PerformerDataSource,
        @Named("cache")  localCacheDataSource: PerformerDataSource
    ): PerformerRepository =
        PerformerRepositoryImpl(remoteDataSource, localCacheDataSource)

    @Singleton
    @Provides
    fun provideCollectorRepository(
        @Named("remote")  remoteDataSource: CollectorDataSource,
        @Named("cache")  localCacheDataSource: CollectorDataSource
    ): CollectorRepository =
        CollectorRepositoryImpl(remoteDataSource, localCacheDataSource)

}
