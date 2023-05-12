package com.grupo15.vinilos.presentation.performers.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grupo15.vinilos.MainCoroutineRule
import com.grupo15.vinilos.data.repository.performer.PerformerRepository
import com.grupo15.vinilos.presentation.performers.getFakePerformer
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
class PerformerDetailViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private val performerRepository: PerformerRepository = mockk()
    private lateinit var viewModel: PerformerDetailViewModel

    @Before
    fun setup() {
        viewModel = PerformerDetailViewModel(performerRepository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `success when getPerformer`() = runTest {
        val idPerformer = 1
        val fakePerformer = getFakePerformer(idPerformer)
        coEvery { performerRepository.getPerformer(any()) } returns Result.success(fakePerformer)
        viewModel.getPerformer(idPerformer)
        coVerify { performerRepository.getPerformer(any()) }
        Assert.assertEquals(fakePerformer.id, viewModel.performer.value?.id)
        Assert.assertEquals(fakePerformer.name, viewModel.performer.value?.name)
        Assert.assertEquals(fakePerformer.description, viewModel.performer.value?.description)
        Assert.assertEquals(fakePerformer.image, viewModel.performer.value?.image)
        Assert.assertEquals(fakePerformer.birthDate, viewModel.performer.value?.birthDate)
        Assert.assertEquals(
            fakePerformer.performerPrizes,
            viewModel.performer.value?.performerPrizes
        )
        Assert.assertEquals(
            fakePerformer.albums,
            viewModel.performer.value?.albums
        )
    }

    @Test
    fun `failure when getPerformer`() = runTest {
        val idPerformer = 1
        val message = "Error from api"
        coEvery { performerRepository.getPerformer(any()) } returns Result.failure(Exception(message))
        viewModel.getPerformer(idPerformer)
        coVerify { performerRepository.getPerformer(any()) }
        Assert.assertEquals(message, viewModel.error.value)
    }

}
