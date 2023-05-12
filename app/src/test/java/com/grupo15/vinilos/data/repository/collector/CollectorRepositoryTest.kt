package com.grupo15.vinilos.data.repository.collector

import com.grupo15.vinilos.data.datasource.collector.CollectorDataSource
import com.grupo15.vinilos.presentation.collectors.getFakeCollectors
import com.grupo15.vinilos.presentation.collectors.getFakeCollector
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
class CollectorRepositoryTest {

    private val collectorRemoteDataSource = mockk<CollectorDataSource>()
    private val collectorLocalCacheDataSource = mockk<CollectorDataSource>()
    private lateinit var collectorRepository: CollectorRepository

    @Before
    fun setup() {
        collectorRepository = CollectorRepositoryImpl(collectorRemoteDataSource, collectorLocalCacheDataSource)
    }

    @Test
    fun `success when getCollectors`() = runTest {
        // given
        val collectors = getFakeCollectors()
        coEvery { collectorRemoteDataSource.getCollectors() } returns Result.success(collectors)

        // when
        val result = collectorRepository.getCollectors()

        // then
        coVerify { collectorRemoteDataSource.getCollectors() }
        assertEquals(Result.success(collectors), result)
    }

    @Test
    fun `failure when getCollectors`() = runTest {
        // given
        val message = "Error from api"
        coEvery { collectorRemoteDataSource.getCollectors() } returns Result.failure(Exception(message))

        // when
        val result = collectorRepository.getCollectors()

        // then
        coVerify { collectorRemoteDataSource.getCollectors() }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

    @Test
    fun `success when getCollector`() = runTest {
        // given
        val performer = getFakeCollector(1)
        coEvery {  collectorDataSource.getCollector (any()) } returns Result.success(performer)

        // when
        val result = collectorRepository.getCollector("100")

        // then
        coVerify { collectorDataSource.getCollector(any()) }
        assertEquals(Result.success(performer), result)
    }

    @Test
    fun `failure when getCollector`() = runTest {
        // given
        val message = "Error from api"
        coEvery { collectorDataSource.getCollector(any()) } returns Result.failure(Exception(message))

        // when
        val result = collectorRepository.getCollector("100")

        // then
        coVerify { collectorDataSource.getCollector(any()) }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

}
