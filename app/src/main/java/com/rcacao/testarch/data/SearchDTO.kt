package com.rcacao.testarch.data

data class SearchDTO constructor(var result: String = "", var message: String = "") {

    companion object {
        const val RESULT_INACTIVE = "INACTIVE"
        const val RESULT_BLOCKED = "BLOCKED"
        const val RESULT_OK = "OK"
    }
}