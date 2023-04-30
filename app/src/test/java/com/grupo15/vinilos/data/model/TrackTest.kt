package com.grupo15.vinilos.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class TrackTest {

    @Test
    fun testTrackProperties() {
        val track = Track(
            1, "Track Name", "3:42"
        )

        assertEquals(1, track.id)
        assertEquals("Track Name", track.name)
        assertEquals("3:42", track.duration)
    }

}
