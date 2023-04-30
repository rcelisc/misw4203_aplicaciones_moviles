package com.grupo15.vinilos.di

import com.grupo15.vinilos.data.datasource.album.AlbumDataSource
import com.grupo15.vinilos.data.datasource.collector.CollectorDataSource
import com.grupo15.vinilos.data.datasource.performer.PerformerDataSource
import com.grupo15.vinilos.data.repository.album.AlbumRepositoryImpl
import com.grupo15.vinilos.data.repository.collector.CollectorRepositoryImpl
import com.grupo15.vinilos.data.repository.performer.PerformerRepositoryImpl
import org.junit.Test
import org.mockito.Mockito.mock

class RepositoryModuleTest {

    private val albumDataSource = mock(AlbumDataSource::class.java)
    private val performerDataSource = mock(PerformerDataSource::class.java)
    private val collectorDataSource = mock(CollectorDataSource::class.java)

    @Test
    fun `provideAlbumRepository should return AlbumRepositoryImpl`() {
        val albumRepository = RepositoryModule().provideAlbumRepository(albumDataSource)
        assert(albumRepository is AlbumRepositoryImpl)
    }

    @Test
    fun `providePerformerRepository should return PerformerRepositoryImpl`() {
        val performerRepository = RepositoryModule().providePerformerRepository(performerDataSource)
        assert(performerRepository is PerformerRepositoryImpl)
    }

    @Test
    fun `provideCollectorRepository should return CollectorRepositoryImpl`() {
        val collectorRepository = RepositoryModule().provideCollectorRepository(collectorDataSource)
        assert(collectorRepository is CollectorRepositoryImpl)
    }

}
