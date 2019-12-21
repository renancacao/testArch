package com.rcacao.testarch.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rcacao.testarch.data.SearchRepository
import com.rcacao.testarch.domain.SearchCodeUseCase
import kotlinx.coroutines.launch

class SearchViewModel(repository: SearchRepository) : ViewModel() {

    private val searchCodeUseCase = SearchCodeUseCase(repository)
    private val _state = MutableLiveData<SearchState>()
    var state: LiveData<SearchState>  = _state

    fun searchCode(code: String) {
        _state.value = SearchState.Loading
        viewModelScope.launch {
            _state.value = searchCodeUseCase(code)
        }

    }

}