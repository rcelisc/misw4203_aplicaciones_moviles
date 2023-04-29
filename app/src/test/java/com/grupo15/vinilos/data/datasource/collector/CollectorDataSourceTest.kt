package com.grupo15.vinilos.data.datasource.collector

import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import com.grupo15.vinilos.presentation.collectors.getFakeCollectors
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import java.lang.Exception
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CollectorDataSourceTest {

    private val vinilosServiceAdapter = mockk<VinilosServiceAdapter>()
    private lateinit var collectorDataSource: CollectorDataSource

    @Before
    fun setup() {
        collectorDataSource = CollectorDataSourceImpl(vinilosServiceAdapter)
    }

    @Test
    fun `success when getCollectors`() = runTest {
        // given
        val collectors = getFakeCollectors()
        coEvery { vinilosServiceAdapter.getCollectors() } returns Result.success(collectors)

        // when
        val result = collectorDataSource.getCollectors()

        // then
        coVerify { vinilosServiceAdapter.getCollectors() }
        assertEquals(Result.success(collectors), result)
    }

    @Test
    fun `failure when getCollectors`() = runTest {
        // given
        val message = "Error from api"
        coEvery { vinilosServiceAdapter.getCollectors() } returns Result.failure(Exception(message))

        // when
        val result = collectorDataSource.getCollectors()

        // then
        coVerify { vinilosServiceAdapter.getCollectors() }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

}
