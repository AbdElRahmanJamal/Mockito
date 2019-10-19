package com.m.mockito

import junit.framework.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class MockInjectAnnotation {
    @Test
    fun test() {
        val myList = mock(List::class.java)
        //mock behaviour of mockList object when i called .size function return 5
        `when`(myList.size).thenReturn(1)
        assertTrue(myList.size == 1)
    }
}