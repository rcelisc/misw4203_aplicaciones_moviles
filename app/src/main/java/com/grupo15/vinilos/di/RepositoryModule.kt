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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAlbumRepository(albumDataSource: AlbumDataSource): AlbumRepository =
        AlbumRepositoryImpl(albumDataSource)

    @Singleton
    @Provides
    fun providePerformerRepository(performerDataSource: PerformerDataSource): PerformerRepository =
        PerformerRepositoryImpl(performerDataSource)

    @Singleton
    @Provides
    fun provideCollectorRepository(collectorDataSource: CollectorDataSource): CollectorRepository =
        CollectorRepositoryImpl(collectorDataSource)

}
