package com.grupo15.vinilos.data.model

import org.junit.Test
import org.junit.Assert.assertEquals
import java.util.Date

class AlbumTest {

    @Test
    fun testAlbumProperties() {
        val releaseDate = Date()
        val album = Album(
            1, "Album Name", "cover.jpg", releaseDate, "Album description",
            "Rock", "Record Label", listOf(), listOf(), listOf()
        )

        assertEquals(1, album.id)
        assertEquals("Album Name", album.name)
        assertEquals("cover.jpg", album.cover)
        assertEquals(releaseDate, album.releaseDate)
        assertEquals("Album description", album.description)
        assertEquals("Rock", album.genre)
        assertEquals("Record Label", album.recordLabel)
        assertEquals(0, album.tracks.size)
        assertEquals(0, album.performers.size)
        assertEquals(0, album.comments.size)
    }
}
