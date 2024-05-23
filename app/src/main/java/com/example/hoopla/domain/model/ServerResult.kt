package com.example.hoopla.domain.model

/**
 * server result class to model web response from server
 */
data class ServerResult<out T> (
    var status: Status,
    val data: T?,
    val error: ErrorResponse?,
    val message: String?
) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {

        fun<T> loading (data:T? = null): ServerResult<T> {
            return ServerResult(Status.LOADING, data, null, null)
        }

        fun<T> success (data:T?): ServerResult<T> {
            return ServerResult(Status.SUCCESS, data, null, null)
        }

        fun<T> error (message: String, error: ErrorResponse?): ServerResult<T> {
            return ServerResult(Status.ERROR, null, error, message)
        }
    }
}