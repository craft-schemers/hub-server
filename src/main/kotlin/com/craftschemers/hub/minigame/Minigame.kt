package com.craftschemers.hub.minigame

import com.craftschemers.hub.Hub
import com.craftschemers.hub.HubPlayer
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location

abstract class Minigame(
    val name: String,
    val displayName: String,
    val type: GameType,
    val minPlayers: Int,
    val maxPlayers: Int,
    val maxServers: Int = 1,
) {

    var lobbyLocation: Location? = null

    init {
        val configFile = Hub.plugin.config
        val worldName = configFile.getString("$name.lobby.world")

        val x = configFile.getDouble("$name.lobby.x")
        val y = configFile.getDouble("$name.lobby.y")
        val z = configFile.getDouble("$name.lobby.z")

        val world = worldName?.let { Bukkit.getWorld(it) }

        lobbyLocation = worldName?.let { Location(world, x, y, z) }
    }

    open val lobbies = mutableListOf<Lobby>()

    fun addPlayer(player: HubPlayer) {
        if (maxServers == 0) {
            player.sendErrorMessage("$displayName has no active lobbies!")
            return
        }
        val lobby: Lobby?
        if (lobbies.size == 0) {
            lobby = createNewLobby()
            lobby.addPlayer(player)
            lobbies.add(lobby)
        } else {
            // find first available lobby or make a new one
            lobby = lobbies.find { it.state == GameState.LOBBY }
            if (lobby == null && lobbies.size < maxServers) {
                // create a new lobby
                val newLobby = createNewLobby()
                newLobby.players.add(player)
                lobbies.add(newLobby)
                return
            } else if (lobby == null || lobby.players.size >= maxPlayers) {
                // spectate (cant create any more lobbies)
                TODO()
            } else {
                lobby.addPlayer(player)
            }
        }
        lobby.players.forEach {
            it.player.sendMessage(
                "${ChatColor.GRAY}${player.player.name}${ChatColor.YELLOW} has joined " +
                        "(${ChatColor.AQUA}${lobby.players.size}${ChatColor.YELLOW}/${ChatColor.AQUA}$maxPlayers" +
                        "${ChatColor.YELLOW})!"
            )
        }
    }

    abstract fun createNewLobby(): Lobby
    abstract fun handlePlayerLeave(player: HubPlayer)

}
