package com.rcacao.testarch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val searchCodeUseCase = SearchCodeUseCase(SearchRepository())
    private val _state = MutableLiveData<SearchState>()
    var state: LiveData<SearchState>

    init {
        state = _state
    }

    fun searchCode(code: String) {

        _state.value = SearchState.Clean
        _state.value = SearchState.Loading

        viewModelScope.launch {
            _state.value = searchCodeUseCase(code)
        }

    }

}