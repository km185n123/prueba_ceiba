package com.example.contactos.utils

sealed class ResultDataApi<T> {
    data class Success<T>(val value: T) : ResultDataApi<T>()
    data class Failure<T>(val throwable: Throwable) : ResultDataApi<T>()
}