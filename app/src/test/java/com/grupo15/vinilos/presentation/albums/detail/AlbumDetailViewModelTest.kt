package com.grupo15.vinilos.presentation.albums.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grupo15.vinilos.MainCoroutineRule
import com.grupo15.vinilos.data.repository.album.AlbumRepository
import com.grupo15.vinilos.presentation.albums.getFakeAlbum
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
class AlbumDetailViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private val albumRepository: AlbumRepository = mockk()
    private lateinit var viewModel: AlbumDetailViewModel

    @Before
    fun setup() {
        viewModel = AlbumDetailViewModel(albumRepository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `success when getAlbum`() = runTest {
        val idAlbum = 1
        val fakeAlbum = getFakeAlbum(idAlbum)
        coEvery { albumRepository.getAlbum(any()) } returns Result.success(fakeAlbum)
        viewModel.getAlbum(idAlbum)
        coVerify { albumRepository.getAlbum(any()) }
        Assert.assertEquals(fakeAlbum.id, viewModel.album.value?.id)
        Assert.assertEquals(fakeAlbum.name, viewModel.album.value?.name)
        Assert.assertEquals(fakeAlbum.cover, viewModel.album.value?.cover)
        Assert.assertEquals(fakeAlbum.releaseDate, viewModel.album.value?.releaseDate)
        Assert.assertEquals(fakeAlbum.genre, viewModel.album.value?.genre)
        Assert.assertEquals(fakeAlbum.recordLabel, viewModel.album.value?.recordLabel)
        Assert.assertEquals(fakeAlbum.description, viewModel.album.value?.description)
        Assert.assertEquals(
            fakeAlbum.tracks,
            viewModel.album.value?.tracks
        )
        Assert.assertEquals(fakeAlbum.comments, viewModel.album.value?.comments)
        Assert.assertEquals(
            fakeAlbum.performers,
            viewModel.album.value?.performers
        )
    }

    @Test
    fun `failure when getAlbum`() = runTest {
        val idAlbum = 1
        val message = "Error from api"
        coEvery { albumRepository.getAlbum(any()) } returns Result.failure(Exception(message))
        viewModel.getAlbum(idAlbum)
        coVerify { albumRepository.getAlbum(any()) }
        Assert.assertEquals(message, viewModel.error.value)
    }

}
