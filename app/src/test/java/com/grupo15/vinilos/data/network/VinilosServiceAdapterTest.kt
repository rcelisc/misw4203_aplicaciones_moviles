package com.grupo15.vinilos.data.network

import com.grupo15.vinilos.data.network.utils.ResponseException
import com.grupo15.vinilos.presentation.albums.getFakeAlbums
import com.grupo15.vinilos.presentation.collectors.getFakeCollector
import com.grupo15.vinilos.presentation.collectors.getFakeCollectors
import com.grupo15.vinilos.presentation.performers.getFakePerformer
import com.grupo15.vinilos.presentation.performers.getFakePerformers
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class VinilosServiceAdapterTest {

    private val vinilosApi = mockk<VinilosApi>()
    private lateinit var vinilosServiceAdapter: VinilosServiceAdapter

    @Before
    fun setup() {
        vinilosServiceAdapter = VinilosServiceAdapterImpl(vinilosApi)
    }

    @Test
    fun `success when getAlbums`() = runTest {
        // given
        val albums = getFakeAlbums()
        coEvery { vinilosApi.getAlbums() } returns Response.success(albums)

        // when
        val result = vinilosServiceAdapter.getAlbums()

        // then
        coVerify { vinilosApi.getAlbums() }
        Assert.assertEquals(Result.success(albums), result)
    }

    @Test
    fun `failure when getAlbums`() = runTest {
        // given
        val message = ResponseException.NoConnectionException()
        coEvery { vinilosApi.getAlbums() } throws message

        // when
        val result = vinilosServiceAdapter.getAlbums()

        // then
        coVerify { vinilosApi.getAlbums() }
        Assert.assertEquals(message.message, result.exceptionOrNull()?.message)
    }

    @Test
    fun `success when getPerformers`() = runTest {
        // given
        val performers = getFakePerformers()
        coEvery { vinilosApi.getPerformers() } returns Response.success(performers)

        // when
        val result = vinilosServiceAdapter.getPerformers()

        // then
        coVerify { vinilosApi.getPerformers() }
        Assert.assertEquals(Result.success(performers), result)
    }

    @Test
    fun `failure when getPerformers`() = runTest {
        // given
        val message = ResponseException.NoConnectionException()
        coEvery { vinilosApi.getPerformers() } throws message

        // when
        val result = vinilosServiceAdapter.getPerformers()

        // then
        coVerify { vinilosApi.getPerformers() }
        Assert.assertEquals(message.message, result.exceptionOrNull()?.message)
    }

    @Test
    fun `success when getCollectors`() = runTest {
        // given
        val collectors = getFakeCollectors()
        coEvery { vinilosApi.getCollectors() } returns Response.success(collectors)

        // when
        val result = vinilosServiceAdapter.getCollectors()

        // then
        coVerify { vinilosApi.getCollectors() }
        Assert.assertEquals(Result.success(collectors), result)
    }

    @Test
    fun `failure when getCollectors`() = runTest {
        // given
        val message = ResponseException.NoConnectionException()
        coEvery { vinilosApi.getCollectors() } throws message

        // when
        val result = vinilosServiceAdapter.getCollectors()

        // then
        coVerify { vinilosApi.getCollectors() }
        Assert.assertEquals(message.message, result.exceptionOrNull()?.message)
    }

    @Test
    fun `success when getPerformer`() = runTest {
        // given
        val performer = getFakePerformer(1)
        coEvery { vinilosApi.getPerformer(any()) } returns Response.success(performer)

        // when
        val result = vinilosServiceAdapter.getPerformer(1)

        // then
        coVerify { vinilosApi.getPerformer(any()) }
        Assert.assertEquals(Result.success(performer), result)
    }

    @Test
    fun `failure when getPerformer`() = runTest {
        // given
        val message = ResponseException.NoConnectionException()
        coEvery { vinilosApi.getPerformer(any()) } throws message

        // when
        val result = vinilosServiceAdapter.getPerformer(1)

        // then
        coVerify { vinilosApi.getPerformer(any()) }
        Assert.assertEquals(message.message, result.exceptionOrNull()?.message)
    }


    @Test
    fun `success when getCollector`() = runTest {
        // given
        val performer = getFakeCollector(1)
        coEvery { vinilosApi.getCollector(any()) } returns Response.success(performer)

        // when
        val result = vinilosServiceAdapter.getCollector("100")

        // then
        coVerify { vinilosApi.getCollector(any()) }
        Assert.assertEquals(Result.success(performer), result)
    }
    @Test
    fun `failure when getCollector`() = runTest {
        // given
        val message = ResponseException.NoConnectionException()
        coEvery { vinilosApi.getCollector (any()) } throws message

        // when
        val result = vinilosServiceAdapter.getCollector("100")

        // then
        coVerify { vinilosApi.getCollector(any()) }
        Assert.assertEquals(message.message, result.exceptionOrNull()?.message)
    }


}
