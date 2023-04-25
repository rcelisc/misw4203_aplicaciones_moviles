package com.grupo15.vinilos.data.datasource.performer

import com.grupo15.vinilos.data.model.Performer

interface PerformerDataSource {

    fun getPerformers(): List<Performer>

}
