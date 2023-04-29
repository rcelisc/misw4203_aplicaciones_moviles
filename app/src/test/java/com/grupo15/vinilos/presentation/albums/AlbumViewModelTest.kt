package com.grupo15.vinilos.presentation.albums

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grupo15.vinilos.MainCoroutineRule
import com.grupo15.vinilos.data.repository.album.AlbumRepository
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
class AlbumViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private val albumRepository: AlbumRepository = mockk()
    private lateinit var viewModel: AlbumViewModel

    @Before
    fun setup() {
        viewModel = AlbumViewModel(albumRepository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `success when getAlbums`() = runTest {
        // given
        val fakeAlbums = getFakeAlbums()
        coEvery { albumRepository.getAlbums() } returns Result.success(fakeAlbums)

        // when
        viewModel.getAlbums()

        // then
        coVerify { albumRepository.getAlbums() }
        Assert.assertEquals(fakeAlbums.size, viewModel.albums.value?.size)
    }

    @Test
    fun `failure when getAlbums`() = runTest {
        // given
        val message = "Error from api"
        coEvery { albumRepository.getAlbums() } returns Result.failure(Exception(message))

        // when
        viewModel.getAlbums()

        // then
        coVerify { albumRepository.getAlbums() }
        Assert.assertEquals(message, viewModel.error.value)
    }

}
