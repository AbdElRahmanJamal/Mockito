package com.m.mockito.calculator

class DataBaseOperations {

    private var str: String? = null

    fun insertStringIntoDatabase(str: String) {
        this.str = str
    }

    fun getStr() = this.str

}
