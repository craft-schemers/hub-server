package com.craftschemers.hub

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class Events : Listener {

    /*
    General event listeners that will always apply (will never be unregistered)
    Let's keep most of the player-related logic out of this class and inside of the HubPlayerManager
     */

    private val plugin = Hub.plugin
    private val playerDataManager = plugin.playerManger

    @EventHandler
    fun onPlayerConnect(event: PlayerJoinEvent) {
        playerDataManager.addPlayer(event.player)
    }

    @EventHandler
    fun onPlayerDisconnect(event: PlayerQuitEvent) {
        playerDataManager.removePlayer(event.player)
    }

}
