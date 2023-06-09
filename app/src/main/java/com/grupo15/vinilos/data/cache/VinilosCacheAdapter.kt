package com.grupo15.vinilos.data.cache

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track

interface VinilosCacheAdapter {

    fun getAlbums(): List<Album>

    fun getCollectors(): List<Collector>

    fun getPerformers(): List<Performer>

    fun getAlbum(albumId: Int): Album?

    fun getCollector(collectorId: Int): Collector?

    fun getPerformer(performerId: Int): Performer?

    fun createAlbum(album: Album):Album

    fun saveCollector(collector: Collector)

    fun savePerformer(performer: Performer)

    //fun setTrackToAlbum(id: Int, track: Track): SetTrackResponse
}