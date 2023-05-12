package com.grupo15.vinilos.data.repository.album

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.repository.performer.PerformerRepository
import com.grupo15.vinilos.presentation.albums.getFakeAlbum
import com.grupo15.vinilos.presentation.albums.getFakeAlbums
import com.grupo15.vinilos.presentation.performers.getFakePerformer
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

    private val albumDataSource = mockk<AlbumDataSource>()
    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setup() {
        albumRepository = AlbumRepositoryImpl(albumDataSource)
    }

    @Test
    fun `success when getAlbums`() = runTest {
        // given
        val albums = getFakeAlbums()
        coEvery { albumDataSource.getAlbums() } returns Result.success(albums)

        // when
        val result = albumRepository.getAlbums()

        // then
        coVerify { albumDataSource.getAlbums() }
        assertEquals(Result.success(albums), result)
    }

    @Test
    fun `failure when getAlbums`() = runTest {
        // given
        val message = "Error from api"
        coEvery { albumDataSource.getAlbums() } returns Result.failure(Exception(message))

        // when
        val result = albumRepository.getAlbums()

        // then
        coVerify { albumDataSource.getAlbums() }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

    @Test
    fun `success when album detail`() = runTest {
        // given
        val album = getFakeAlbum(1)
        coEvery { albumDataSource.getAlbum(any()) } returns Result.success(album)

        // when
        val result = albumRepository.getAlbum(1)

        // then
        coVerify { albumDataSource.getAlbum(any()) }
        assertEquals(Result.success(album), result)
    }

    @Test
    fun `failure when getAlbum`() = runTest {
        // given
        val message = "Error from api"
        coEvery { albumDataSource.getAlbum(any()) } returns Result.failure(Exception(message))

        // when
        val result = albumRepository.getAlbum(1)

        // then
        coVerify { albumDataSource.getAlbum(any()) }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

}
