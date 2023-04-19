package com.grupo15.vinilos.di

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.repository.album.AlbumRepository
import com.grupo15.vinilos.data.repository.album.AlbumRepositoryImpl
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

}
