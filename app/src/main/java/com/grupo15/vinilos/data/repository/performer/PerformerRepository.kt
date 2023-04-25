package com.grupo15.vinilos.data.repository.performer

import com.grupo15.vinilos.data.model.Performer

interface PerformerRepository {

    fun getPerformers(): List<Performer>

}
