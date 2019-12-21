package com.rcacao.testarch


class SearchCodeUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke(code: String): SearchState {

        return when (val searchResult = repository.search(code)) {

            is ResultSearch.Success -> {
                when (searchResult.data.result) {
                    SearchDTO.RESULT_OK -> SearchState.Success(searchResult.data.message)
                    SearchDTO.RESULT_BLOCKED,
                    SearchDTO.RESULT_INACTIVE -> SearchState.Error(searchResult.data.message)
                    else -> SearchState.Error("Estado do usuário não localizado")
                }
            }
            is ResultSearch.Error -> SearchState.Error(searchResult.exception.message ?: "Internal Error")
            else -> SearchState.Error("unexpected error")
        }
    }
}


