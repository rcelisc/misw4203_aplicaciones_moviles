package com.grupo15.vinilos.di

import kotlinx.coroutines.Dispatchers
import org.junit.Test
import org.junit.Assert.assertEquals

class DispatchersModuleTest {

    @Test
    fun testProvideIoDispatcher() {
        val module = DispatchersModule()
        val ioDispatcher = module.provideIoDispatcher()

        assertEquals(Dispatchers.IO, ioDispatcher)
    }
}
