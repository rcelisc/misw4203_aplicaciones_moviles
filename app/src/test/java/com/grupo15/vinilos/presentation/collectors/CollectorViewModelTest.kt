package com.grupo15.vinilos.presentation.collectors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grupo15.vinilos.MainCoroutineRule
import com.grupo15.vinilos.data.repository.collector.CollectorRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import java.lang.Exception
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class CollectorViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private val collectorRepository: CollectorRepository = mockk()
    private lateinit var viewModel: CollectorViewModel

    @Before
    fun setup() {
        viewModel = CollectorViewModel(collectorRepository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `success when getAlbums`() = runTest {
        val fakeCollectors = getFakeCollectors()
        coEvery { collectorRepository.getCollectors() } returns Result.success(fakeCollectors)
        viewModel.getCollectors()
        coVerify { collectorRepository.getCollectors() }
        Assert.assertEquals(fakeCollectors.size, viewModel.collectors.value?.size)
    }

    @Test
    fun `failure when getAlbums`() = runTest {
        val message = "Error from api"
        coEvery { collectorRepository.getCollectors() } returns Result.failure(Exception(message))
        viewModel.getCollectors()
        coVerify { collectorRepository.getCollectors() }
        Assert.assertEquals(message, viewModel.error.value)
    }

}
