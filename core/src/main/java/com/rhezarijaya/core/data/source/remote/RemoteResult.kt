package com.rhezarijaya.core.data.source.remote

sealed class RemoteResult<out R> {
    data class Success<out T>(val data: T) : RemoteResult<T>()
    data class Error(val exception: Exception) : RemoteResult<Nothing>()
    data object Empty : RemoteResult<Nothing>()
}