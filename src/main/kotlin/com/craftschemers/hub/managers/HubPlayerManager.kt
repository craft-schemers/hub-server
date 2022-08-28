package com.craftschemers.hub.managers

import com.craftschemers.hub.HubPlayer
import org.bukkit.GameMode
import org.bukkit.entity.Player
import java.util.*

class HubPlayerManager {
    private val players = mutableMapOf<UUID, HubPlayer>()

    fun addPlayer(player: Player) {
        players[player.uniqueId] = HubPlayer(player)
        player.gameMode = GameMode.ADVENTURE
        player.inventory.clear()
    }

    fun hasPlayer(uuid: UUID) = uuid in players

    fun getPlayer(uuid: UUID) = players[uuid]

}
