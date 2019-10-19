package com.m.mockito.calculator

class MainClass(private val operation: DataBaseOperations) {

    fun insertIntoDataBase(str: String) {
        operation.insertStringIntoDatabase(str)
    }

    fun getDataBaseRecord() = operation.getStr()

}