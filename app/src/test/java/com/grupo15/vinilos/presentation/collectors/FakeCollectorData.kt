package com.grupo15.vinilos.presentation.collectors

import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.model.CollectorAlbum

fun getFakeCollectors(): List<Collector> {
    val collectorList = mutableListOf<Collector>()
    for (i in 1..10) {
        val collector = Collector(
            id = i,
            name = "Collector $i",
            telephone = "555-555-${i.toString().padStart(4, '0')}",
            email = "collector$i@example.com",
            comments = null,
            favoritePerformers = null,
            collectorAlbums = listOf(
                CollectorAlbum(
                    id = 1,
                    price = (10..50).random(),
                    status = if (i % 2 == 0) "available" else "unavailable"
                )
            )
        )
        collectorList.add(collector)
    }
    return collectorList
}
