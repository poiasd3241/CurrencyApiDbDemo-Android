package com.poiasd.currencyapidbdemo.util

/**
 * Simple data state container with two possible states: [Success] and [Error].
 *
 * @param T The type of data that will be used by the state's consumer.
 */
sealed class DataState<T> {

    /**
     * Container for the result of a successful operation.
     *
     * @param data The successfully obtained data.
     * @param message The information about the obtained data (its origin, for example).
     */
    data class Success<T>(val data: T, val message: String) : DataState<T>()

    /**
     * Container for the information about an error.
     *
     * @param errorMessage The information about the error (its origin, for example).
     */
    data class Error<T>(val errorMessage: String) : DataState<T>()
}
