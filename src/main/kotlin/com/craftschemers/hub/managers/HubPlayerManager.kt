package com.craftschemers.hub.managers

import com.craftschemers.hub.Hub
import com.craftschemers.hub.HubPlayer
import org.bukkit.GameMode
import org.bukkit.entity.Player
import java.util.*

class HubPlayerManager {
    private val players = mutableMapOf<UUID, HubPlayer>()
    private val gameManager = Hub.plugin.gameManager

    fun addPlayer(player: Player) {
        players[player.uniqueId] = HubPlayer(player)
        player.gameMode = GameMode.ADVENTURE
        player.inventory.clear()
    }

    fun removePlayer(player: Player): Boolean {
        val hubPlayer = players[player.uniqueId] ?: return false
        gameManager.removePlayerFromMinigame(hubPlayer)
        return true
    }

    fun hasPlayer(uuid: UUID) = uuid in players

    fun getPlayer(uuid: UUID) = players[uuid]

}
