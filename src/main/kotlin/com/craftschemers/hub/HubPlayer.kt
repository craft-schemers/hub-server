package com.craftschemers.hub

import com.craftschemers.hub.minigame.GameType
import org.bukkit.entity.Player

class HubPlayer(val player: Player) {
    var game: GameType? = null
}
