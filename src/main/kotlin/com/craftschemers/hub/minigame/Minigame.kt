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
        val z = configFile.getDouble("$name.lobby.z")

        val world = worldName?.let { Bukkit.getWorld(it) }

        lobbyLocation =
            worldName?.let {
                world?.getHighestBlockYAt(x.toInt(), z.toInt())
                    ?.let { it1 -> Location(world, x, it1.toDouble() + 1, z) }
            }
    }

    open val lobbies = mutableListOf<AbstractGameData>()

    fun addPlayer(player: HubPlayer) {
        println("attempting to add $player")
        if (maxServers == 0) return
        val lobby: AbstractGameData?
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
        for (p in lobby.players) {
            p.player.sendMessage(
                "${ChatColor.GRAY}${player.player.name}${ChatColor.YELLOW} has joined " +
                        "(${ChatColor.AQUA}${lobby.players.size}${ChatColor.YELLOW}/${ChatColor.AQUA}$maxPlayers" +
                        "${ChatColor.YELLOW})!"
            )
        }
    }

    abstract fun createNewLobby(): AbstractGameData
    abstract fun handlePlayerLeave(player: HubPlayer)

}
