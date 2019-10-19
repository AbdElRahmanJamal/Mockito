package com.m.mockito

import android.content.Context
import com.m.mockito.mockitoclasses.StringUtility
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class StringUtilityTest {
    @Mock
    lateinit var mockUtil: StringUtility


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun allCharacterToUpperCaseTest() {

        `when`(mockUtil.allCharacterToUpperCase("Abdo")).thenReturn("ABDO")
        val actualResult = mockUtil.allCharacterToUpperCase("Abdo")
        assertEquals("ABDO", actualResult)

    }
}
