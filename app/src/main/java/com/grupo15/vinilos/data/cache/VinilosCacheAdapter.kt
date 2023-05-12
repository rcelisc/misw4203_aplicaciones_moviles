package com.grupo15.vinilos.data.cache

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.Performer

interface VinilosCacheAdapter {

    fun getAlbums(): List<Album>

    fun getCollectors(): List<Collector>

    fun getPerformers(): List<Performer>

    fun getAlbum(albumId: Int): Album?

    fun getCollector(collectorId: Int): Collector?

    fun getPerformer(performerId: Int): Performer?

    fun saveAlbum(album: Album)

    fun saveCollector(collector: Collector)

    fun savePerformer(performer: Performer)

}