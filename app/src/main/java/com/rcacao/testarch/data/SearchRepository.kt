package com.rcacao.testarch.data

interface SearchRepository {
    suspend fun search(code: String): ResultSearch<SearchDTO>
    suspend fun isValidCode(code: String) : Boolean
}
