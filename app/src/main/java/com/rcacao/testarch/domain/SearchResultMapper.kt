package com.rcacao.testarch.domain

import com.rcacao.testarch.data.ResultSearch
import com.rcacao.testarch.data.SearchDTO
import com.rcacao.testarch.search.SearchState

class SearchResultMapper {

    fun map(searchResult: ResultSearch<SearchDTO>): SearchState {
        return when (searchResult) {

            is ResultSearch.Success -> {
                when (searchResult.data.result) {
                    SearchDTO.RESULT_OK -> SearchState.Success(searchResult.data.message)
                    SearchDTO.RESULT_BLOCKED,
                    SearchDTO.RESULT_INACTIVE -> SearchState.Error(searchResult.data.message)
                    else -> SearchState.Error("Estado do usuário não localizado")
                }
            }
            is ResultSearch.Error -> SearchState.Error(
                searchResult.exception.message ?: "Internal Error"
            )
        }
    }

}
