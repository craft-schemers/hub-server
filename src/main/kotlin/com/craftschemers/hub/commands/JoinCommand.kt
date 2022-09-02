package com.craftschemers.hub.commands

import com.craftschemers.hub.Hub
import com.craftschemers.hub.minigame.Minigame
import com.craftschemers.hub.minigame.getGameTypeFromName
import com.craftschemers.hub.minigame.sendErrorMessage
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

    override fun onCommand(sender: CommandSender, minigame: Minigame?, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendErrorMessage("You must be a player to execute this command!")
            return true
        }

        if (args.size != 1) {
            sender.sendErrorMessage("Please provide a game you would like to join!")
            return true
        }

        val game = getGameTypeFromName(args[0]) ?: run {
            sender.sendErrorMessage("Invalid game!")
            return true
        }

        val hubPlayer = plugin.playerManger.getPlayer(sender.uniqueId)
        if (hubPlayer == null) {
            sender.sendErrorMessage("Something went wrong!")
            return true
        }

        if (hubPlayer.game != null) {
            sender.sendErrorMessage("You're already in a game!")
            return true
        }

        plugin.playerManger.getPlayer(sender.uniqueId)?.let { plugin.gameManager.addPlayerToMinigame(it, game) }
            ?: run {
                sender.sendErrorMessage("Something went wrong.")
                return true
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
