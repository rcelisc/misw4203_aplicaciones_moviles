package com.grupo15.vinilos.data.datasource.performer

import com.grupo15.vinilos.data.network.VinilosServiceAdapter
import com.grupo15.vinilos.presentation.performers.getFakePerformer
import com.grupo15.vinilos.presentation.performers.getFakePerformers
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
class PerformerDataSourceTest {

    private val vinilosServiceAdapter = mockk<VinilosServiceAdapter>()
    private lateinit var performerDataSource: PerformerDataSource

    @Before
    fun setup() {
        performerDataSource = PerformerDataSourceImpl(vinilosServiceAdapter)
    }

    @Test
    fun `success when getPerformers`() = runTest {
        // given
        val performers = getFakePerformers()
        coEvery { vinilosServiceAdapter.getPerformers() } returns Result.success(performers)

        // when
        val result = performerDataSource.getPerformers()

        // then
        coVerify { vinilosServiceAdapter.getPerformers() }
        assertEquals(Result.success(performers), result)
    }

    @Test
    fun `failure when getPerformers`() = runTest {
        // given
        val message = "Error from api"
        coEvery { vinilosServiceAdapter.getPerformers() } returns Result.failure(Exception(message))

        // when
        val result = performerDataSource.getPerformers()

        // then
        coVerify { vinilosServiceAdapter.getPerformers() }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

    @Test
    fun `success when performer detail`() = runTest {
        // given
        val performer = getFakePerformer(1)
        coEvery { vinilosServiceAdapter.getPerformer(any()) } returns Result.success(performer)

        // when
        val result = performerDataSource.getPerformer("1")

        // then
        coVerify { vinilosServiceAdapter.getPerformer(any()) }
        assertEquals(Result.success(performer), result)
    }

    @Test
    fun `failure when getPerformer`() = runTest {
        // given
        val message = "Error from api"
        coEvery { vinilosServiceAdapter.getPerformer(any()) } returns Result.failure(Exception(message))

        // when
        val result = performerDataSource.getPerformer("1")

        // then
        coVerify { vinilosServiceAdapter.getPerformer(any()) }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

}
