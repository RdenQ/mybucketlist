package com.rdenq.bucketlist.data.network

data class ApiError(val status: ApiStatus, val code: Int = -1, var message: String = "") {

    fun getErrorMessage(): String {
        if (message.isNullOrEmpty()) {
            message = when (status) {
                ApiStatus.EMPTY_RESPONSE -> "no data available in repository"
                ApiStatus.NO_CONNECTION -> "error in connecting to repository"
                ApiStatus.BAD_RESPONSE -> "error in getting response"
                ApiStatus.TIMEOUT -> "time out error"
                ApiStatus.NOT_DEFINED -> "an unexpected error"
            }
        }

        return message
    }

    // unlike Java, in Kotlin its 'enum class' not 'enum'
    enum class ApiStatus {
        NO_CONNECTION,
        BAD_RESPONSE,
        TIMEOUT,
        EMPTY_RESPONSE,
        NOT_DEFINED;
    }

}