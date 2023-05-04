package com.grupo15.vinilos.data.repository.performer

import com.grupo15.vinilos.data.datasource.performer.PerformerDataSource
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
class PerformerRepositoryTest {

    private val performerDataSource = mockk<PerformerDataSource>()
    private lateinit var performerRepository: PerformerRepository

    @Before
    fun setup() {
        performerRepository = PerformerRepositoryImpl(performerDataSource)
    }

    @Test
    fun `success when getPerformers`() = runTest {
        // given
        val performers = getFakePerformers()
        coEvery { performerDataSource.getPerformers() } returns Result.success(performers)

        // when
        val result = performerRepository.getPerformers()

        // then
        coVerify { performerDataSource.getPerformers() }
        assertEquals(Result.success(performers), result)
    }

    @Test
    fun `failure when getPerformers`() = runTest {
        // given
        val message = "Error from api"
        coEvery { performerDataSource.getPerformers() } returns Result.failure(Exception(message))

        // when
        val result = performerRepository.getPerformers()

        // then
        coVerify { performerDataSource.getPerformers() }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

    @Test
    fun `success when getPerformer`() = runTest {
        // given
        val performer = getFakePerformer(1)
        coEvery { performerDataSource.getPerformer(any()) } returns Result.success(performer)

        // when
        val result = performerRepository.getPerformer("1")

        // then
        coVerify { performerDataSource.getPerformer(any()) }
        assertEquals(Result.success(performer), result)
    }

    @Test
    fun `failure when getPerformer`() = runTest {
        // given
        val message = "Error from api"
        coEvery { performerDataSource.getPerformer(any()) } returns Result.failure(Exception(message))

        // when
        val result = performerRepository.getPerformer("1")

        // then
        coVerify { performerDataSource.getPerformer(any()) }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

}
