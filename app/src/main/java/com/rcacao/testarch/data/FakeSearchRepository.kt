package com.rcacao.testarch.data

import kotlinx.coroutines.delay

class FakeSearchRepository : SearchRepository {

    override suspend fun search(code: String): ResultSearch<SearchDTO> {

        delay(1500)

       return when (code) {
            FAKE_OK -> ResultSearch.Success(
                SearchDTO(
                    SearchDTO.RESULT_OK,
                    "Usuário Ativo!"
                )
            )
            FAKE_INACTIVE -> ResultSearch.Success(
                SearchDTO(
                    SearchDTO.RESULT_INACTIVE,
                    "Usuário Inativo!"
                )
            )
            FAKE_BLOCKED ->  ResultSearch.Success(
                SearchDTO(
                    SearchDTO.RESULT_BLOCKED,
                    "Usuário Bloqueado!"
                )
            )
            else ->  ResultSearch.Error(Exception("User not found"))
        }
    }

    companion object {
        const val FAKE_OK = "0000"
        const val FAKE_INACTIVE = "1111"
        const val FAKE_BLOCKED = "2222"
    }


}
