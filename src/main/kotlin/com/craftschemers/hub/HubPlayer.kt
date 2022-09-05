package com.craftschemers.hub

import com.craftschemers.hub.minigame.GameType
import com.craftschemers.hub.minigame.sendErrorMessage
import org.bukkit.entity.Player

data class HubPlayer(val player: Player) {
    var game: GameType? = null

    fun sendMessage(message: String) {
        player.sendMessage(message)
    }

    fun sendErrorMessage(message: String) {
        player.sendErrorMessage(message)
    }
}
