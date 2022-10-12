package com.alecbrando.musicplayer.util

sealed class Resource<T>(data: T? = null, message: String? = null){
    data class Success<T>(val data: T): Resource<T>(data)
    class Loading<T>: Resource<T>()
    data class Error<T>(val message: String): Resource<T>(message = message)
}
