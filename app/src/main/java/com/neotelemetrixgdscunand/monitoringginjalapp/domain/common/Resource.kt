package com.neotelemetrixgdscunand.monitoringginjalapp.domain.common

import com.fajar.githubuserappdicoding.core.domain.common.StringRes

sealed class Resource<out T> private constructor() {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure(val message: StringRes) : Resource<Nothing>()
    data class Error(val e: Exception) : Resource<Nothing>()
    suspend fun handleAsync(
        onSuccess:(T) -> Unit = { },
        onFailure: suspend (StringRes) -> Unit = { },
        onError: suspend (Exception) -> Unit = { }
    ){
        when(this){
            is Success -> onSuccess(data)
            is Failure -> onFailure(message)
            is Error -> onError(e)
        }
    }

    fun handle(
        onSuccess:(T) -> Unit = { },
        onFailure: (StringRes) -> Unit = { },
        onError: (Exception) -> Unit = { }
    ){
        when(this){
            is Success -> onSuccess(data)
            is Failure -> onFailure(message)
            is Error -> onError(e)
        }
    }
}