package com.grupo15.vinilos.di

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.datasource.album.AlbumDataSourceImpl
import com.grupo15.vinilos.data.datasource.performer.PerformerDataSource
import com.grupo15.vinilos.data.datasource.performer.PerformerDataSourceImpl
import com.grupo15.vinilos.data.network.VinilosApi
import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import com.grupo15.vinilos.data.network.VinilosServiceAdapterImpl
import com.grupo15.vinilos.data.network.utils.DateAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseURL(): String = "https://vinilos-backend-mobile.herokuapp.com"

    @Singleton
    @Provides
    fun provideJsonConverter(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder().add(DateAdapter()).build()
        )

    @Singleton
    @Provides
    fun provideVinilosApi(
        @Named("BaseUrl") baseUrl: String,
        converter: MoshiConverterFactory
    ): VinilosApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converter)
        .build().create(VinilosApi::class.java)

    @Singleton
    @Provides
    fun provideServiceAdapter(apiService: VinilosApi): VinilosServiceAdapter =
        VinilosServiceAdapterImpl(apiService)

    @Singleton
    @Provides
    fun provideAlbumDataSource(serviceAdapter: VinilosServiceAdapter): AlbumDataSource =
        AlbumDataSourceImpl(serviceAdapter)

    @Singleton
    @Provides
    fun providePerformerDataSource(serviceAdapter: VinilosServiceAdapter): PerformerDataSource =
        PerformerDataSourceImpl(serviceAdapter)

}