package com.example.core.db.uitls

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

sealed class DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val exception: Throwable) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Empty : DataState<Nothing>()
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineContext = Dispatchers.IO,
    apiCall: suspend () -> T?
): T {
    return withContext(dispatcher) {
        try {
            apiCall() ?: throw EmptyException("Network null")
        } catch (error: Throwable) {
            throw error
        }
    }
}

suspend fun <T> MutableStateFlow<DataState<T>>.asMutableStateFlow(
    action: suspend () -> T
) {
    this.update { DataState.Loading }
    try {
        val data = action()
        if (data != null) {
            this.update { DataState.Success(data) }
        } else {
            this.update { DataState.Empty }
        }

    } catch (error: Throwable) {
        this.update { DataState.Error(error) }
    }
}

data class EmptyException(override val message: String) : Exception(message)