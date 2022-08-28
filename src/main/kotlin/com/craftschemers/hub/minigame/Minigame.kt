package com.craftschemers.hub.minigame

import com.craftschemers.hub.HubPlayer

abstract class Minigame(val name: String, val type: GameType) {
    var state = GameState.LOBBY
    private val players = mutableListOf<HubPlayer>()

    fun addPlayer(player: HubPlayer) {
        players += player
    }

    fun hasPlayer(player: HubPlayer): Boolean = player in players

    abstract fun start()
    abstract fun handlePlayerLeave(player: HubPlayer)
}
