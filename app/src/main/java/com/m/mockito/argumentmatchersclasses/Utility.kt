package com.m.mockito.argumentmatchersclasses

import com.m.mockito.injectmocks.Player

class Utility {
    fun isUserNameContainsSpecificNumber(number: Int, player: Player) =
        player.getName().contains(number.toChar())

}