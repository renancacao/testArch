package com.rcacao.testarch

sealed class ResultSearch<out R> {

    data class Success<out T>(val data: T) : ResultSearch<T>()
    data class Error(val exception: Exception) : ResultSearch<Nothing>()

}