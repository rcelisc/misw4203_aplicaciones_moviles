package com.grupo15.vinilos.di

import com.grupo15.vinilos.data.network.VinilosApi
import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkModuleTest {

    @Test
    fun testProvideBaseURL() {
        val networkModule = NetworkModule()
        val baseUrl = networkModule.provideBaseURL()
        assertEquals("https://vinilos-backend-mobile.herokuapp.com", baseUrl)
    }

    @Test
    fun testProvideJsonConverter() {
        val networkModule = NetworkModule()
        val jsonConverter = networkModule.provideJsonConverter()
        assertNotNull(jsonConverter)
    }

    @Test
    fun testProvideVinilosApi() {
        val networkModule = NetworkModule()
        val vinilosApi =
            networkModule.provideVinilosApi("https://example.com", MoshiConverterFactory.create())
        assertNotNull(vinilosApi)
    }

    @Test
    fun testProvideServiceAdapter() {
        val networkModule = NetworkModule()
        val apiService = mockk<VinilosApi>()
        val serviceAdapter = networkModule.provideServiceAdapter(apiService)
        assertNotNull(serviceAdapter)
    }

    @Test
    fun testProvideAlbumDataSource() {
        val networkModule = NetworkModule()
        val serviceAdapter = mockk<VinilosServiceAdapter>()
        val albumDataSource = networkModule.provideAlbumDataSource(serviceAdapter)
        assertNotNull(albumDataSource)
    }

    @Test
    fun testProvidePerformerDataSource() {
        val networkModule = NetworkModule()
        val serviceAdapter = mockk<VinilosServiceAdapter>()
        val performerDataSource = networkModule.providePerformerDataSource(serviceAdapter)
        assertNotNull(performerDataSource)
    }

    @Test
    fun testProvideCollectorDataSource() {
        val networkModule = NetworkModule()
        val serviceAdapter = mockk<VinilosServiceAdapter>()
        val collectorDataSource = networkModule.provideCollectorDataSource(serviceAdapter)
        assertNotNull(collectorDataSource)
    }

}
