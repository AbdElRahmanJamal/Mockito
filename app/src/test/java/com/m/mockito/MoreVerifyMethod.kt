package com.m.mockito

import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*


class MoreVerifyMethod {
    @Test
    fun test() {
        val myList = mock(mutableListOf<String>()::class.java)
        myList.add("Abdo")
        myList.size

        verify(myList).add("Abdo")
    }

    @Test
    fun verifyTimes() {
        val myList = mock(mutableListOf<String>()::class.java)
        myList.add("Abdo")
        myList.size

        verify(myList, times(1)).add("Abdo")
    }

    @Test
    fun verifyAtLeast() {
        val myList = mock(mutableListOf<String>()::class.java)
        myList.add("abdo")
        myList.add("abdo")
        myList.size

        verify(myList, atLeast(1)).add(ArgumentMatchers.anyString())
    }

    @Test
    fun verifyAtMost() {
        val myList = mock(mutableListOf<String>()::class.java)
        myList.add("Abdo")
        myList.size

        verify(myList, atMost(2)).add("Abdo")
    }

    @Test
    fun verifyNever() {
        val myList = mock(mutableListOf<String>()::class.java)
        myList.add("Abdo")
        myList.size

        verify(myList, never()).add("Abdo")
    }

    @Test
    fun verifyOnly() {
        val myList = mock(mutableListOf<String>()::class.java)
        myList.add("Abdo")

        verify(myList, only()).add("Abdo")
    }

    @Test
    fun verifyVerifyNoMoreInteractions() {
        val myList = mock(mutableListOf<String>()::class.java)
        myList.add("Abdo")
        verify(myList, only()).add("Abdo")
        myList.size
        verify(myList, atLeast(1)).size
        verifyNoMoreInteractions(myList)
    }
    @Test
    fun verifyVerifyZeroInteractions() {
        val myList = mock(mutableListOf<String>()::class.java)
        myList.add("Abdo")
        verify(myList, only()).add("Abdo")
        myList.size
        verify(myList, atLeast(1)).size
        myList.removeAt(0)
        verifyZeroInteractions(myList)
    }
}