package com.rcacao.testarch.data

import kotlinx.coroutines.delay

class FakeSearchRepository : SearchRepository {

    //Num caso real, esse retorno seria através de uma API, ou ficaria no UseCase correspondente
    override suspend fun isValidCode(code: String): Boolean {
        delay(2000)
        val regex = "\\d{4}".toRegex()
        return regex.matches(code)
    }

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
            FAKE_BLOCKED -> ResultSearch.Success(
                SearchDTO(
                    SearchDTO.RESULT_BLOCKED,
                    "Usuário Bloqueado!"
                )
            )
            else -> ResultSearch.Error(Exception("User not found"))
        }
    }

    companion object {
        const val FAKE_OK = "0000"
        const val FAKE_INACTIVE = "1111"
        const val FAKE_BLOCKED = "2222"
    }


}
