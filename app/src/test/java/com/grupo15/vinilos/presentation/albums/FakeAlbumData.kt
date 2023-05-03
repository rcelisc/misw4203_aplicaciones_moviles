package com.grupo15.vinilos.presentation.albums

import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.model.PerformerPrize
import com.grupo15.vinilos.data.model.Track
import java.util.Date

fun getFakeAlbums(): List<Album> {
    val albumList = mutableListOf<Album>()
    for (i in 1..10) {
        albumList.add(getFakeAlbum(i))
    }
    return albumList
}

fun getFakeAlbum(i: Int): Album {
    return Album(
        id = i,
        name = "Album $i",
        cover = "https://example.com/album$i.jpg",
        releaseDate = Date(),
        description = "This is album $i",
        genre = "Pop",
        recordLabel = "Record Label $i",
        tracks = listOf(
            Track(1, "Track 1", "3:45"),
            Track(2, "Track 2", "4:12")
        ),
        performers = listOf(
            Performer(
                id = 1,
                name = "Performer 1",
                image = "https://example.com/performer1.jpg",
                description = "This is performer 1",
                birthDate = Date(),
                creationDate = Date(),
                performerPrizes = listOf(
                    PerformerPrize(1, Date()),
                    PerformerPrize(2, Date())
                ),
                albums = emptyList()
            ),
            Performer(
                id = 2,
                name = "Performer 2",
                image = "https://example.com/performer2.jpg",
                description = "This is performer 2",
                birthDate = Date(),
                creationDate = Date(),
                performerPrizes = emptyList(),
                albums = emptyList()
            )
        ),
        comments = emptyList()
    )
}
