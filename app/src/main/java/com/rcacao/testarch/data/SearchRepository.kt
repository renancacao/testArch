package com.rcacao.testarch.data

interface SearchRepository {
    suspend fun search(code: String): ResultSearch<SearchDTO>
}