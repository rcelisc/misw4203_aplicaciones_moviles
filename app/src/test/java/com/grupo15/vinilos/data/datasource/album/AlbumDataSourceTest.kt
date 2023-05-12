package com.grupo15.vinilos.data.datasource.album

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.network.VinilosServiceAdapter
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
class AlbumDataSourceTest {

    private val vinilosServiceAdapter = mockk<VinilosServiceAdapter>()
    private lateinit var albumDataSource: AlbumDataSource

    @Before
    fun setup() {
        albumDataSource = AlbumDataSourceImpl(vinilosServiceAdapter)
    }

    @Test
    fun `success when getAlbums`() = runTest {
        // given
        val albums = getFakeAlbums()
        coEvery { vinilosServiceAdapter.getAlbums() } returns Result.success(albums)

        // when
        val result = albumDataSource.getAlbums()

        // then
        coVerify { vinilosServiceAdapter.getAlbums() }
        assertEquals(Result.success(albums), result)
    }

    @Test
    fun `failure when getAlbums`() = runTest {
        // given
        val message = "Error from api"
        coEvery { vinilosServiceAdapter.getAlbums() } returns Result.failure(Exception(message))

        // when
        val result = albumDataSource.getAlbums()

        // then
        coVerify { vinilosServiceAdapter.getAlbums() }
        assertEquals(message, result.exceptionOrNull()?.message)
    }
    @Test
    fun `success when album detail`() = runTest {
        // given
        val album = getFakeAlbum(1)
        coEvery { vinilosServiceAdapter.getAlbum(any()) } returns Result.success(album)

        // when
        val result = albumDataSource.getAlbum(1)

        // then
        coVerify { vinilosServiceAdapter.getAlbum(any()) }
        assertEquals(Result.success(album), result)
    }

    @Test
    fun `failure when getAlbum`() = runTest {
        // given
        val message = "Error from api"
        coEvery { vinilosServiceAdapter.getAlbum(any()) } returns Result.failure(Exception(message))

        // when
        val result = albumDataSource.getAlbum(1)

        // then
        coVerify { vinilosServiceAdapter.getAlbum(any()) }
        assertEquals(message, result.exceptionOrNull()?.message)
    }

}
