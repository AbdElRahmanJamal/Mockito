package com.m.mockito

import com.m.mockito.calculator.DataBaseOperations
import com.m.mockito.calculator.MainClass
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TestmainClassWithInjectMocks {

    @Mock
    lateinit var dataBaseOperations: DataBaseOperations

    @InjectMocks
    lateinit var mainClass: MainClass

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testMainClassInsertIntoDataBase() {

        Mockito.`when`(dataBaseOperations.getStr()).thenReturn("Abdo")
        Assert.assertEquals("Abdo",mainClass.getDataBaseRecord())
    }

    @Test
    fun testMainClassGetDataBaseRecord() {

        Mockito.doAnswer { i ->
            println("Argument = " + i.getArgument(0))

        }.`when`(dataBaseOperations).insertStringIntoDatabase(Mockito.anyString())

        dataBaseOperations.insertStringIntoDatabase("Abdo")
        Mockito.`when`(dataBaseOperations.getStr()).thenReturn("Abdo")
        Assert.assertEquals("Abdo",mainClass.getDataBaseRecord())
    }

}