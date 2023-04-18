package com.grupo15.vinilos.data.datasource.collector

import com.grupo15.vinilos.data.model.Collector

interface CollectorDataSource {

    fun getCollectors(): List<Collector>

}
