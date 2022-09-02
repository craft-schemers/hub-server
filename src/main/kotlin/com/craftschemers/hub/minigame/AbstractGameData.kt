package com.craftschemers.hub.minigame

import com.craftschemers.hub.HubPlayer

abstract class AbstractGameData {
    val players = mutableListOf<HubPlayer>()
    var state = GameState.LOBBY

    abstract fun addPlayer(player: HubPlayer)
    abstract fun startGame()
}
