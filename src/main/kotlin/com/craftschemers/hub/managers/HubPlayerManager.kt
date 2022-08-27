package com.craftschemers.hub.managers

import com.craftschemers.hub.HubPlayer
import org.bukkit.GameMode
import org.bukkit.entity.Player

class HubPlayerManager {
    private var players = mutableMapOf<String, HubPlayer>()

    fun addPlayer(player: Player) {
        players[player.uniqueId.toString()] = HubPlayer(player)
        player.gameMode = GameMode.ADVENTURE
        player.inventory.clear()
    }

}