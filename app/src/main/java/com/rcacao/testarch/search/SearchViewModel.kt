package com.rcacao.testarch.search

import androidx.lifecycle.*
import com.rcacao.testarch.R
import com.rcacao.testarch.data.SearchRepository
import com.rcacao.testarch.domain.SearchCodeUseCase
import com.rcacao.testarch.util.Event
import kotlinx.coroutines.launch

class SearchViewModel(repository: SearchRepository) : ViewModel() {

    private val searchCodeUseCase = SearchCodeUseCase(repository)

    private val _state = MutableLiveData<SearchState>()
    val state: LiveData<SearchState> = _state

    val event = _state.map {
        if (it is SearchState.InvalidCode) {
            Event(R.string.invalid_code)
        } else {
            Event(null)
        }
    }

    fun searchCode(code: String) {
        _state.value = SearchState.Loading
        viewModelScope.launch {
            _state.value = searchCodeUseCase(code)
        }

    }

}