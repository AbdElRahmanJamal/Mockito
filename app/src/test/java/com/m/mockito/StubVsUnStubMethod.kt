package com.m.mockito

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.spy

class StubVsUnStubMethod {
    @Test
    fun testStubbedMethod() {
        val myList = Mockito.mock(mutableListOf<String>()::class.java)
        myList.add("Abdo")
        myList.size
        Assert.assertEquals(1, myList.size)
    }

    @Test
    fun testUnStubbedMethodFail() {
        val myList = Mockito.mock(mutableListOf<String>()::class.java)
        val spyList = spy(myList)
        spyList.add("Abdo")

        Assert.assertEquals(1, spyList.size)
    }

    @Test
    fun testUnStubbedMethodSuccess() {
        val list = mutableListOf<String>()
        val spyList = spy(list)
        spyList.add("Abdo")
        spyList.add("CR")

        junit.framework.Assert.assertEquals(2, spyList.size)

    }
}