package com.craftschemers.hub

import org.bukkit.entity.Player

class HubPlayerManager {
    private var players = mutableMapOf<String, HubPlayer>()

    fun addPlayer(player: Player) {
        players[player.name] = HubPlayer(player)
    }

}
