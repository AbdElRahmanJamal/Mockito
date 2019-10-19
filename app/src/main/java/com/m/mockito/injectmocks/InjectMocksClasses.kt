package com.m.mockito.injectmocks

class Club(private val players: List<Player>) {

    fun getPlayers() = players
}

class Player(private val name: String) {
    fun getName() = name
}