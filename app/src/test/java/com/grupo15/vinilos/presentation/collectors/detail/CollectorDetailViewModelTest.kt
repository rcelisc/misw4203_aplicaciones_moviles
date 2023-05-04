package com.grupo15.vinilos.presentation.collectors.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grupo15.vinilos.MainCoroutineRule
import com.grupo15.vinilos.data.repository.collector.CollectorRepository
import com.grupo15.vinilos.presentation.collectors.getFakeCollector
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
class CollectorDetailViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private val collectorRepository: CollectorRepository = mockk()
    private lateinit var viewModel: CollectorDetailViewModel

    @Before
    fun setup() {
        viewModel = CollectorDetailViewModel(collectorRepository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `success when getCollector`() = runTest {
        val idCollector = 1
        val fakeCollector = getFakeCollector(idCollector)
        coEvery { collectorRepository.getCollector(any()) } returns Result.success(fakeCollector)
        viewModel.getCollector("$idCollector")
        coVerify { collectorRepository.getCollector(any()) }
        Assert.assertEquals(fakeCollector.id, viewModel.collector.value?.id)
        Assert.assertEquals(fakeCollector.name, viewModel.collector.value?.name)
        Assert.assertEquals(fakeCollector.email, viewModel.collector.value?.email)
        Assert.assertEquals(fakeCollector.telephone, viewModel.collector.value?.telephone)
        Assert.assertEquals(
            fakeCollector.collectorAlbums,
            viewModel.collector.value?.collectorAlbums
        )
        Assert.assertEquals(fakeCollector.comments, viewModel.collector.value?.comments)
        Assert.assertEquals(
            fakeCollector.favoritePerformers,
            viewModel.collector.value?.favoritePerformers
        )
    }

    @Test
    fun `failure when getCollector`() = runTest {
        val idCollector = 1
        val message = "Error from api"
        coEvery { collectorRepository.getCollector(any()) } returns Result.failure(Exception(message))
        viewModel.getCollector("$idCollector")
        coVerify { collectorRepository.getCollector(any()) }
        Assert.assertEquals(message, viewModel.error.value)
    }

}
