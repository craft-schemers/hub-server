package com.craftschemers.hub

import org.bukkit.GameMode
import org.bukkit.entity.Player

class HubPlayerManager {
    private var players = mutableMapOf<String, HubPlayer>()

    fun addPlayer(player: Player) {
        players[player.name] = HubPlayer(player)
        player.gameMode = GameMode.ADVENTURE
        player.inventory.clear()
    }

}
