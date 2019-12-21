package com.rcacao.testarch

sealed class SearchState {

    data class Success(val message: String) : SearchState()
    data class Error(val message: String) : SearchState()
    object Clean : SearchState()
    object Loading : SearchState()
}
