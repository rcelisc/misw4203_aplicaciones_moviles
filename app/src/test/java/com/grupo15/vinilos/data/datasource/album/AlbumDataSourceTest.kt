package com.grupo15.vinilos.data.datasource.album

import com.grupo15.vinilos.data.network.VinilosServiceAdapter
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
class AlbumDataSourceTest {

    private val vinilosServiceAdapter = mockk<VinilosServiceAdapter>()
    private lateinit var albumDataSource: AlbumDataSource

    @Before
    fun setup() {
        albumDataSource = RemoteAlbumDataSourceImpl(vinilosServiceAdapter)
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

}
