package com.m.mockito

import com.m.mockito.argumentmatchersclasses.Utility
import com.m.mockito.injectmocks.Player
import org.junit.Assert
import org.junit.Test
import org.mockito.AdditionalMatchers.aryEq
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

fun <T> anyOrNull(): T = Mockito.any<T>()


class AnyEq {
    @Test
    fun testAny() {
        val utility = mock(Utility::class.java)
        `when`(
            utility.isUserNameContainsSpecificNumber(
                anyInt(),
                anyOrNull()
            )
        ).thenReturn(false)

        Assert.assertEquals(true, utility.isUserNameContainsSpecificNumber(1, Player("Abd1")));
    }

    @Test
    fun testEqSuccess() {
        val utility = mock(Utility::class.java)
        `when`(
            utility.isUserNameContainsSpecificNumber(
                eq(5),
                anyOrNull()
            )
        ).thenReturn(true)

        Assert.assertEquals(true, utility.isUserNameContainsSpecificNumber(5, Player("Abd1")));
    }

    @Test
    fun testEqFail() {
        val utility = mock(Utility::class.java)
        `when`(
            utility.isUserNameContainsSpecificNumber(
                eq(1),
                anyOrNull()
            )
        ).thenReturn(true)

        Assert.assertEquals(true, utility.isUserNameContainsSpecificNumber(5, Player("Abd1")));
    }

}