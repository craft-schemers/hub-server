package com.craftschemers.hub.minigame

import com.craftschemers.hub.HubPlayer

abstract class Minigame(val name: String, val type: GameType) {
    var state = GameState.LOBBY
    abstract fun start()
}
