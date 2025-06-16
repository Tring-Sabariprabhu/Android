package com.example.roomdatabase.networkResponse

sealed interface NetworkResponse<out T> {
    data class Success<T>(val value: T): NetworkResponse<T>
    data class Error(val exception: Exception): NetworkResponse<Nothing>
    object Loading: NetworkResponse<Nothing>
}