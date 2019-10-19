package com.m.mockito

import com.m.mockito.injectmocks.Club
import com.m.mockito.injectmocks.Player
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class InjectMocksTest {

    @Mock
    lateinit var players: List<Player>

    @InjectMocks
    lateinit var club: Club // here mockito will make something like it club(payers)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testMockAndInjectMock() {
        Mockito.`when`(players[0]).thenReturn(Player("Abdo"))
        Mockito.`when`(players.size).thenReturn(1)

        Assert.assertEquals("Abdo",club.getPlayers()[0].getName())
    }
    @Test
    fun testMockAndInjectMock2() {
        Mockito.`when`(players.size).thenReturn(10)

        Assert.assertEquals(10,club.getPlayers().size)
    }
}