package com.grupo15.vinilos.data.model

import org.junit.Test
import org.junit.Assert.assertEquals

class CollectorTest {

    @Test
    fun testCollectorProperties() {

        val collector = Collector(
            1,
            "Collector Name",
            "555-555-5555",
            "collector@example.com",
            listOf(),
            listOf(),
            listOf()
        )

        assertEquals(1, collector.id)
        assertEquals("Collector Name", collector.name)
        assertEquals("555-555-5555", collector.telephone)
        assertEquals("collector@example.com", collector.email)
        assertEquals(0, collector.comments?.size)
        assertEquals(0, collector.favoritePerformers?.size)
        assertEquals(0, collector.collectorAlbums?.size)
    }

    @Test
    fun testCollectorAlbumProperties() {
        val collectorAlbum = CollectorAlbum(1, 100, "Available")

        assertEquals(1, collectorAlbum.id)
        assertEquals(100, collectorAlbum.price)
        assertEquals("Available", collectorAlbum.status)
    }

    @Test
    fun testCommentProperties() {
        val comment = Comment(1, "Great collector!", 5)

        assertEquals(1, comment.id)
        assertEquals("Great collector!", comment.description)
        assertEquals(5, comment.rating)
    }

}
