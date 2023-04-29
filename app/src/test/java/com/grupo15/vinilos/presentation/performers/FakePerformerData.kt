package com.grupo15.vinilos.presentation.performers

import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.model.PerformerPrize
import java.util.Date

fun getFakePerformers(): List<Performer> {
    val performerList = mutableListOf<Performer>()
    for (i in 1..10) {
        val performer =
            Performer(
                id = 1,
                name = "Performer $i",
                image = "https://example.com/performer$i.jpg",
                description = "This is performer $i",
                birthDate = Date(),
                creationDate = Date(),
                performerPrizes = listOf(
                    PerformerPrize(1, Date()),
                    PerformerPrize(2, Date())
                ),
                albums = emptyList()
            )
        performerList.add(performer)
    }
    return performerList
}
