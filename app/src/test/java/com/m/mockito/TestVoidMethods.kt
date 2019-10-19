package com.m.mockito

import com.m.mockito.voidmethodtest.Student
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.*


class TestVoidMethods {

    @Test
    fun testMockitoVoidMethodDoThrow() {
        //here test wil fail bcs null pointer exception
        val student = mock(Student::class.java)
        doThrow(NullPointerException::class.java).`when`(student).getStudentName()
        assertEquals("Abdo", student.getStudentName())
    }

    @Test
    fun testMockitoVoidMethodDoAnswer() {
        val student = mock(Student::class.java)

        doAnswer { i ->
            println("Argument = " + i.getArgument(0))

        }.`when`(student).setStudentName(anyString())

        student.setStudentName("abdo")
        `when`(student.getStudentName()).thenReturn("Abdo")
        assertEquals("Abdo", student.getStudentName());

    }

}