package com.grupo15.vinilos.data.model

import java.util.Date
import org.junit.Assert.assertEquals
import org.junit.Test

class PerformerTest {

    @Test
    fun testPerformerProperties() {
        val birthdate = Date()
        val creationDate = Date()

        val performer = Performer(
            1,
            "Performer Name",
            "performer.jpg",
            "Performer description",
            birthdate,
            creationDate,
            listOf(),
            listOf()
        )

        assertEquals(1, performer.id)
        assertEquals("Performer Name", performer.name)
        assertEquals("performer.jpg", performer.image)
        assertEquals("Performer description", performer.description)
        assertEquals(birthdate, performer.birthDate)
        assertEquals(creationDate, performer.creationDate)
        assertEquals(0, performer.performerPrizes?.size)
        assertEquals(0, performer.albums?.size)
    }

    @Test
    fun testPerformerPrizeProperties() {
        val prizeDate = Date()
        val prize = PerformerPrize(
            1, prizeDate
        )
        assertEquals(1, prize.id)
        assertEquals(prizeDate, prize.prizeDate)
    }

}
