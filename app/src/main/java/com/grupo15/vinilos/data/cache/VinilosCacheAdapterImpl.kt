package com.grupo15.vinilos.data.cache

import android.util.LruCache
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.data.model.Track

class VinilosCacheAdapterImpl : VinilosCacheAdapter {

    private var albums: LruCache<Int, Album> = LruCache(3)
    private var collectors: LruCache<Int, Collector> = LruCache(3)
    private var performers: LruCache<Int, Performer> = LruCache(3)

    override fun getAlbums(): List<Album> {
        return albums.snapshot().values.toList()
    }

    override fun getCollectors(): List<Collector> {
        return collectors.snapshot().values.toList()
    }

    override fun getPerformers(): List<Performer> {
        return performers.snapshot().values.toList()
    }

    override fun getAlbum(albumId: Int): Album? = albums[albumId]

    override fun getCollector(collectorId: Int): Collector? = collectors[collectorId]

    override fun getPerformer(performerId: Int): Performer? = performers[performerId]

    override fun createAlbum(album: Album):Album {
        if (albums[album.id] == null) {
            albums.put(album.id, album)
        }
        return album
    }

    override fun saveCollector(collector: Collector) {
        if (collectors[collector.id] == null) {
            collectors.put(collector.id, collector)
        }
    }

    override fun savePerformer(performer: Performer) {
        if (performers[performer.id] == null) {
            performers.put(performer.id, performer)
        }
    }


}