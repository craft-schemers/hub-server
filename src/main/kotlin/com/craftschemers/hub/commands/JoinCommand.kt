package com.craftschemers.hub.commands

import com.craftschemers.hub.Hub
import com.craftschemers.hub.minigame.Minigame
import com.craftschemers.hub.minigame.getGameTypeFromName
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object JoinCommand : ICommand {

    override val name = "join"
    override val aliases = null
    override val canBeConsole = false
    override val description = "Use this command to join a minigame"
    override val parameters = null
    override val usage = arrayOf("/hub join <Minigame>")
    override val permissionMessage = "Permission message"
    override val permission = "permission"

    private val plugin = Hub.plugin
    private val gameManager = plugin.gameManager
    private val playerManager = plugin.playerManger

    override fun onCommand(sender: CommandSender, minigame: Minigame?, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}You must be a player to execute this command!")
            return true
        }

        if (args.isEmpty() || args.size != 1) {
            sender.sendMessage("${ChatColor.RED}Please provide a game you would like to join!")
            return true
        }

        val game = getGameTypeFromName(args[0]) ?: run {
            sender.sendMessage("${ChatColor.RED}Invalid game!")
            return true
        }

        playerManager.getPlayer(sender.uniqueId)?.let { gameManager.addPlayerToMinigame(it, game) }
            ?: sender.sendMessage("${ChatColor.RED}Something went wrong.")

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
