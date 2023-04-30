package com.grupo15.vinilos.data.network.utils

import retrofit2.Response

object ServiceMapper {

    private fun <T> Response<T>.isErrorCode(): Exception? {
        return ResponseException.getException(code())
    }

    fun <T> Response<T>.toResult(): Result<T> = try {
        val errorCode = isErrorCode()
        when {
            errorCode != null -> Result.failure(errorCode)
            isSuccessful -> {
                val body = body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(ResponseException.NotContentException())
                }
            }
            else -> Result.failure(ResponseException.InvalidRequestException())
        }
    } catch (e: Exception) {
        Result.failure(ResponseException.UnknownErrorException())
    }
}
