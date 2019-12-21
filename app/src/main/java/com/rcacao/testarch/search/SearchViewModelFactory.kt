package com.rcacao.testarch.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rcacao.testarch.data.FakeSearchRepository

class SearchViewModelFactory : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(FakeSearchRepository()) as T
    }
}