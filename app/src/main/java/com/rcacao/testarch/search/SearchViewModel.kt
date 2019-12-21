package com.rcacao.testarch.search

import androidx.lifecycle.*
import com.rcacao.testarch.domain.SearchCodeUseCase
import com.rcacao.testarch.data.FakeSearchRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val searchCodeUseCase =
        SearchCodeUseCase(FakeSearchRepository())
    private val _state = MutableLiveData<SearchState>()
    var state: LiveData<SearchState>

    init {
        state = _state
    }

    fun searchCode(code: String) {

        _state.value = SearchState.Loading

        viewModelScope.launch {
            _state.value = searchCodeUseCase(code)
        }

    }

}