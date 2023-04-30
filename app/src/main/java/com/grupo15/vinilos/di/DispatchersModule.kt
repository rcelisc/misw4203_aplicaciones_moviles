package com.grupo15.vinilos.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @IODispatcher
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    annotation class IODispatcher

}
