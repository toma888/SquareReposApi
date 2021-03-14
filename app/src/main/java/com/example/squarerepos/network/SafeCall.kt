package com.example.squarerepos.network


sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Failure(val throwable: Throwable) : ResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultWrapper<T> = try {
    ResultWrapper.Success(apiCall.invoke())
} catch (throwable: Throwable) {
    ResultWrapper.Failure(throwable)
}
