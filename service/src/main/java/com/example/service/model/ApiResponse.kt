package com.example.service.model

import retrofit2.Response
import java.io.IOException

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {
    companion object {
        private const val NO_CONTENT_CODE = 204

        fun <T> create(error: Throwable, url: String): ApiResponse<T> {
            val errorMessage = error.message ?: "unknown error"
            return if (error is IOException) {
                // network error
                ApiOfflineResponse(errorMessage, url)
            } else {
                // app error for example gson parsing error
                ApiErrorResponse(errorMessage, 0)
            }
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == NO_CONTENT_CODE) {
                    ApiErrorResponse("unknown error", 0)
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val responseCode = response.code()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg ?: "unknown error", responseCode)
            }
        }
    }
}

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String, val responseCode: Int) : ApiResponse<T>()

data class ApiOfflineResponse<T>(val errorMessage: String, val url: String) : ApiResponse<T>()
