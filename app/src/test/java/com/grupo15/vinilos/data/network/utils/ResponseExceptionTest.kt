package com.grupo15.vinilos.data.network.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ResponseExceptionTest {

    @Test
    fun testGetException() {
        val errorCode503 = 503
        val errorCode500 = 500
        val errorCode404 = 404
        val errorCode400 = 400
        val errorCode401 = 401
        val errorCode403 = 403
        val errorCode200 = 200

        val expectedException503 = ResponseException.ServiceUnavailableException()
        val expectedException500 = ResponseException.InternalServerException()
        val expectedException404 = ResponseException.NotFoundException()
        val expectedException400 = ResponseException.InvalidRequestException()
        val expectedException401 = ResponseException.UnauthorizedTokenException()
        val expectedException403 = ResponseException.ScopeException()

        val actualException503 = ResponseException.getException(errorCode503)
        val actualException500 = ResponseException.getException(errorCode500)
        val actualException404 = ResponseException.getException(errorCode404)
        val actualException400 = ResponseException.getException(errorCode400)
        val actualException401 = ResponseException.getException(errorCode401)
        val actualException403 = ResponseException.getException(errorCode403)
        val actualException200 = ResponseException.getException(errorCode200)
        val unknownErrorException = ResponseException.UnknownErrorException()

        assertEquals(expectedException503.javaClass, actualException503?.javaClass)
        assertEquals(expectedException500.javaClass, actualException500?.javaClass)
        assertEquals(expectedException404.javaClass, actualException404?.javaClass)
        assertEquals(expectedException400.javaClass, actualException400?.javaClass)
        assertEquals(expectedException401.javaClass, actualException401?.javaClass)
        assertEquals(expectedException403.javaClass, actualException403?.javaClass)
        assertTrue(unknownErrorException is ResponseException)
        assertEquals(null, actualException200)
    }
}
