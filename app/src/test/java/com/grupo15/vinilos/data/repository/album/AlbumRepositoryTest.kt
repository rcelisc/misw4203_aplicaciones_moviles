package com.grupo15.vinilos.data.repository.album

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track
import com.grupo15.vinilos.presentation.albums.getFakeAlbum
import com.grupo15.vinilos.presentation.albums.getFakeAlbums
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
class AlbumRepositoryTest {

    private val albumRemoteDataSource = mockk<AlbumDataSource>()
    private val albumLocalCacheDataSource = mockk<AlbumDataSource>()
    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setup() {
        albumRepository = AlbumRepositoryImpl(albumRemoteDataSource, albumLocalCacheDataSource)
    }

    @Test
    fun `success when getAlbums`() = runTest {
        // given
        val albums = getFakeAlbums()
        coEvery { albumRemoteDataSource.getAlbums() } returns Result.success(albums)

        // when
        val result = albumRepository.getAlbums()

        // then
        coVerify { albumRemoteDataSource.getAlbums() }
        assertEquals(Result.success(albums), result)
    }

    @Test
    fun `failure when getAlbums`() = runTest {
        // given
        val message = "Error from api"
        coEvery { albumRemoteDataSource.getAlbums() } returns Result.failure(Exception(message))

        // when
        val result = albumRepository.getAlbums()

        // then
        coVerify { albumRemoteDataSource.getAlbums() }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

    @Test
    fun `success when album detail`() = runTest {
        // given
        val album = getFakeAlbum(1)
        coEvery { albumLocalCacheDataSource.getAlbum(any()) } returns Result.success(album)

        // when
        val result = albumRepository.getAlbum(1)

        // then
        coVerify { albumLocalCacheDataSource.getAlbum(any()) }
        assertEquals(Result.success(album), result)
    }

    @Test
    fun `success when track to album`() = runTest {
        // given
        val setTrackResponse = SetTrackResponse(101,"track 1", "05:00", getFakeAlbum(1))
        coEvery { albumRemoteDataSource.setTrackToAlbum(any(),any()) } returns Result.success(setTrackResponse)

        // when
        val result = albumRepository.setTrackToAlbum(1, track = Track(101,"track 1", "05:00"))

        coVerify { albumRemoteDataSource.setTrackToAlbum(any(),any()) }

        assertEquals(Result.success(setTrackResponse),result)
    }

    @Test
    fun `success when create album`() = runTest {
        // given
        val album = getFakeAlbum(1)
        coEvery { albumRemoteDataSource.createAlbum(any()) } returns Result.success(album)

        // when
        val result = albumRepository.createAlbum(album)

        coVerify { albumRemoteDataSource.createAlbum(any()) }

        assertEquals(Result.success(album),result)
    }

}
