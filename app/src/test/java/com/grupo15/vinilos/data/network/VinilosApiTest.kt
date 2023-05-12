package com.grupo15.vinilos.data.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class VinilosApiTest {

    lateinit var vinilosApi: VinilosApi

    @Before
    fun setUp() {
        vinilosApi = FakeVinilosApi()
    }

    @Test
    fun `success when getAlbums`() = runTest {
        val response = vinilosApi.getAlbums()
        assertNotNull(response.body())
        assertEquals(10, response.body()?.size)
    }

    @Test
    fun `success when getPerformers`() = runTest {
        val response = vinilosApi.getPerformers()
        assertNotNull(response.body())
        assertEquals(10, response.body()?.size)
    }

    @Test
    fun `success when getCollectors`() = runTest {
        val response = vinilosApi.getCollectors()
        assertNotNull(response.body())
        assertEquals(10, response.body()?.size)
    }

    @Test
    fun `success when getPerformer`() = runTest {
        val response = vinilosApi.getPerformer(1)
        assertNotNull(response.body())
        assertEquals(1, response.body()?.id)
    }

    @Test
    fun `success when getCollector`() = runTest {
        val response = vinilosApi.getCollector("1")
        assertNotNull(response.body())
        assertEquals(1, response.body()?.id)
    }


}

