package com.grupo15.vinilos.data.network.utils

import com.squareup.moshi.Moshi
import java.util.Date
import org.junit.Assert
import org.junit.Test

class DateAdapterTest {

    private val moshi = Moshi.Builder()
        .add(DateAdapter())
        .build()

    @Test
    fun testDateAdapter() {
        val expectedDate = Date()
        val adapter = moshi.adapter(Date::class.java)
        val jsonString = adapter.toJson(expectedDate)
        val actualDate = adapter.fromJson(jsonString)
        Assert.assertEquals(expectedDate, actualDate)
    }

}
