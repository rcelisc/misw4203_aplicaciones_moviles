package com.grupo15.vinilos

import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testAssertions() {
        // Arrange
        val str1 = "Hello"
        val str2 = "Hello"
        val str3 = "World"

        val array1 = arrayOf(1, 2, 3)
        val array2 = arrayOf(1, 2, 3)

        // Act & Assert
        // assertEquals - Comprueba si dos valores son iguales
        assertEquals(4, 2 + 2)


        // assertTrue / assertFalse - Comprueba si una condición es verdadera o falsa
        assertTrue("La condición es verdadera", 5 > 3)
        assertFalse("La condición es falsa", 10 < 2)


        // assertArrayEquals - Comprueba si dos matrices son iguales
        assertArrayEquals("Las matrices son iguales", array1, array2)


        // assertNull / assertNotNull - Comprueba si un objeto es nulo o no nulo
        assertNull("El objeto es nulo", null)
        assertNotNull("El objeto no es nulo", str1)


        // assertSame / assertNotSame - Comprueba si dos referencias se refieren al mismo objeto o no
        assertSame("Las referencias son las mismas", str1, str2)
        assertNotSame("Las referencias no son las mismas", str1, str3)


        // assertThrows - Comprueba si se lanza una excepción
        assertThrows("Se lanza una IllegalArgumentException", IllegalArgumentException::class.java) {
            throw IllegalArgumentException("Invalid argument")
        }
    }
}
