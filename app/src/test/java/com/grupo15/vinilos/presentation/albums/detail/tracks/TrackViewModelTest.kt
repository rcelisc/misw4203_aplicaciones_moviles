package com.grupo15.vinilos.presentation.albums.detail.tracks

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
class TrackViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private val albumRepository: AlbumRepository = mockk()
    private lateinit var viewModel: TrackViewModel

    @Before
    fun setup() {
        viewModel = TrackViewModel(albumRepository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `success when setTrackToAlbum`() = runTest {
        //Given
        val idTrack = 1
        val idAlbum = 1
        val fakeTrackResponse = getFakeTrackResponse(idTrack)
        coEvery { albumRepository.setTrackToAlbum(any(), any()) } returns Result.success(
            fakeTrackResponse
        )

        //When
        viewModel.setTrackToAlbum(
            albumId = idAlbum,
            nameTrack = fakeTrackResponse.name,
            duration = fakeTrackResponse.duration
        )

        //Then
        coVerify { albumRepository.setTrackToAlbum(any(), any()) }
        Assert.assertNotNull(viewModel.tracks.value)
    }

    @Test
    fun `failure when setTrackToAlbum`() = runTest {
        //Given
        val message = "Error from api"
        val idTrack = 1
        val idAlbum = 1
        val fakeTrackResponse = getFakeTrackResponse(idTrack)
        coEvery { albumRepository.setTrackToAlbum(any(), any()) } returns Result.failure(Exception(message))

        //When
        viewModel.setTrackToAlbum(
            albumId = idAlbum,
            nameTrack = fakeTrackResponse.name,
            duration = fakeTrackResponse.duration
        )

        // Then
        coVerify { albumRepository.setTrackToAlbum(any(), any()) }
        Assert.assertEquals(message, viewModel.error.value)
    }

}
