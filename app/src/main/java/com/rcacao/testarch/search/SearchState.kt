package com.rcacao.testarch.search

sealed class SearchState {

    data class Success(val message: String) : SearchState()
    data class Error(val message: String) : SearchState()
    object Loading : SearchState()
    object InvalidCode : SearchState()
}
