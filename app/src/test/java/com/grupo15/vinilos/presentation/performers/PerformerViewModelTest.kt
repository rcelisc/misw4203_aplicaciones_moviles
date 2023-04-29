package com.grupo15.vinilos.presentation.performers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grupo15.vinilos.MainCoroutineRule
import com.grupo15.vinilos.data.repository.performer.PerformerRepository
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
class PerformerViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private val performerRepository: PerformerRepository = mockk()
    private lateinit var viewModel: PerformerViewModel

    @Before
    fun setup() {
        viewModel = PerformerViewModel(performerRepository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `success when getPerformers`() = runTest {
        val fakePerformers = getFakePerformers()
        coEvery { performerRepository.getPerformers() } returns Result.success(fakePerformers)
        viewModel.getPerformers()
        coVerify { performerRepository.getPerformers() }
        Assert.assertEquals(fakePerformers.size, viewModel.performers.value?.size)
    }

    @Test
    fun `failure when getPerformers`() = runTest {
        val message = "Error from api"
        coEvery { performerRepository.getPerformers() } returns Result.failure(Exception(message))
        viewModel.getPerformers()
        coVerify { performerRepository.getPerformers() }
        Assert.assertEquals(message, viewModel.error.value)
    }

}
