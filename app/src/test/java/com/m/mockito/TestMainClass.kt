package com.m.mockito

import com.m.mockito.calculator.DataBaseOperations
import com.m.mockito.calculator.MainClass
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TestMainClass {
    @Mock
    lateinit var dataBaseOperations: DataBaseOperations

    lateinit var mainClass: MainClass

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainClass = MainClass(dataBaseOperations)
    }

    @Test
    fun testMainClassInsertIntoDataBase() {

        mainClass.insertIntoDataBase("Abdo")
        verify(dataBaseOperations).insertStringIntoDatabase("Abdo")
    }

    @Test
    fun testMainClassGetDataBaseRecord() {

        mainClass.getDataBaseRecord()
        verify(dataBaseOperations).insertStringIntoDatabase("abdo")
    }
    @Test
    fun testSpyObject() {
        val spyMainClass= spy(mainClass)
        spyMainClass.insertIntoDataBase("Abdo")
        Assert.assertEquals("Abdo",spyMainClass.getDataBaseRecord())
    }
}


