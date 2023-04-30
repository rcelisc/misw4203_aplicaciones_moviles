package com.grupo15.vinilos.data.network.utils

import com.grupo15.vinilos.data.network.utils.ServiceMapper.toResult
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

class ServiceMapperTest {

    @Test
    fun testToResultSuccess() {
        val expectedBody = "Response body"
        val response = Response.success(expectedBody)

        val result = response.toResult()

        assertTrue(result.isSuccess)
        assertEquals(expectedBody, result.getOrNull())
    }

    @Test
    fun testToResultNullBody() {
        val response = Response.success(null)

        val result = response.toResult()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ResponseException.NotContentException)
    }

    @Test
    fun testToResultError() {
        val response = Response.error<String>(400, ResponseBody.create(null,""))

        val result = response.toResult()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ResponseException.InvalidRequestException)
    }

    @Test
    fun testToResultUnknownError() {
        val response = Response.error<String>(500, ResponseBody.create(null,""))

        val result = response.toResult()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ResponseException.InternalServerException)
    }

}
