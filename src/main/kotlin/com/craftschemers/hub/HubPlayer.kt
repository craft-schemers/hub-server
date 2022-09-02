package com.craftschemers.hub

import com.craftschemers.hub.minigame.GameType
import org.bukkit.entity.Player

data class HubPlayer(val player: Player) {
    var game: GameType? = null
}
