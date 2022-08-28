package com.craftschemers.hub.minigame

import com.craftschemers.hub.HubPlayer

abstract class Minigame(val name: String, val type: GameType) {
    var state = GameState.LOBBY
    fun addPlayer(player: HubPlayer) {
        // implement later
    }
    abstract fun start()
}
