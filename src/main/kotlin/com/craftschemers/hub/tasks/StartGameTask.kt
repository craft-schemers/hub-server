package com.craftschemers.hub.tasks

import com.craftschemers.hub.Hub
import com.craftschemers.hub.minigame.AbstractGameData
import com.craftschemers.hub.minigame.Minigame
import org.bukkit.Bukkit
import org.bukkit.ChatColor

class StartGameTask(private val gameData: AbstractGameData, private val game: Minigame) : Runnable {

    private var time = 10
    private var id = -1
    private var running = false

    override fun run() {
        if (time <= 0) {
            stop()
            gameData.startGame()
            return
        } else if (gameData.players.size < game.minPlayers) {
            stop()
            return
        }
        for (player in gameData.players) {
            player.player.sendMessage("${ChatColor.YELLOW}Game starting in ${ChatColor.RED}$time " +
                    "${ChatColor.YELLOW}seconds!")
        }
        time--
    }

    fun start() {
        time = 10
        id = Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(Hub.plugin, this, 20L, 20L)
        running = true
    }

    private fun stop() {
        if (id != -1 && running) Bukkit.getServer().scheduler.cancelTask(id)
        running = false
    }

}
