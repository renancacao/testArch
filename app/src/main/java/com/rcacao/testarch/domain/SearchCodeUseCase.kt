package com.rcacao.testarch.domain

import com.rcacao.testarch.data.SearchRepository
import com.rcacao.testarch.search.SearchState


class SearchCodeUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke(code: String): SearchState {
        return SearchResultMapper().map(repository.search(code))
    }


}


