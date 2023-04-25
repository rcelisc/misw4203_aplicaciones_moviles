package com.grupo15.vinilos.data.repository.collector

import com.grupo15.vinilos.data.model.Collector

interface CollectorRepository {

    fun getCollectors(): List<Collector>

}
