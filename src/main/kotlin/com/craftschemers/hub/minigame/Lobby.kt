package com.craftschemers.hub.minigame

import com.craftschemers.hub.HubPlayer

abstract class Lobby {
    val players = mutableListOf<HubPlayer>()
    var state = GameState.LOBBY

    abstract fun addPlayer(player: HubPlayer)
    abstract fun startGame()
}
