package com.craftschemers.hub.minigame

import com.craftschemers.hub.HubPlayer

abstract class Minigame(val name: String, val type: GameType) {
    var state = GameState.LOBBY
    private val players = mutableListOf<HubPlayer>()

    fun addPlayer(player: HubPlayer) {
        players += player
    }
    abstract fun start()
}
