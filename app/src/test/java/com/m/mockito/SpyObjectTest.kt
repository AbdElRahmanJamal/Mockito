package com.m.mockito

import com.m.mockito.calculator.DataBaseOperations
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations

class SpyObjectTest {

    lateinit var dataBaseOperations: DataBaseOperations
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataBaseOperations = DataBaseOperations()
    }

    @Test
    fun testSpyObject() {
        val spyMainClass = spy(dataBaseOperations)
        spyMainClass.insertStringIntoDatabase("Abdo")
        Assert.assertEquals("Abdo", spyMainClass.getStr())

    }

    @Test
    fun testSpyObjectArrayList() {
        val list = mutableListOf<String>()
        val spyList = spy(list)
        spyList.add("Abdo")
        spyList.add("CR")

        assertEquals(2, spyList.size)

    }
}