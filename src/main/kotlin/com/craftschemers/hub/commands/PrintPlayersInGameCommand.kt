package com.craftschemers.hub.commands

import com.craftschemers.hub.Hub
import com.craftschemers.hub.minigame.Minigame
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

object PrintPlayersInGameCommand : ICommand {

    override val name = "print"
    override val aliases = null
    override val canBeConsole = true
    override val description = "Use this command to print players in minigames (for debug purposes)"
    override val parameters = null
    override val usage = arrayOf("/hub print")
    override val permissionMessage = "Permission message"
    override val permission = "permission"

    override fun onCommand(sender: CommandSender, minigame: Minigame?, label: String, args: Array<String>): Boolean {
        val gameManager = Hub.plugin.gameManager
        for (value in gameManager.minigames.values) {
            sender.sendMessage("${ChatColor.WHITE}${value.displayName} ${ChatColor.GOLD}has the following lobbies:")
            for ((index, lobby) in value.lobbies.withIndex()) {
                sender.sendMessage("${ChatColor.GOLD}Lobby ${ChatColor.GREEN}${index}: ${lobby.players}")
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        minigame: Minigame,
        alias: String,
        args: Array<String>
    ): List<String> {
        TODO("Not yet implemented")
    }
}
