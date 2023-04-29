package com.grupo15.vinilos.data.datasource.performer

import com.grupo15.vinilos.data.network.VinilosServiceAdapter
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

}
