package com.craftschemers.hub.commands

import com.craftschemers.hub.Hub
import com.craftschemers.hub.minigame.Minigame
import com.craftschemers.hub.minigame.getGameTypeFromName
import com.craftschemers.hub.minigame.sendErrorMessage
import com.craftschemers.hub.minigame.sendSuccessMessage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object LeaveCommand : ICommand {

    override val name = "leave"
    override val aliases = null
    override val canBeConsole = false
    override val description = "Use this command to leave a minigame"
    override val parameters = null
    override val usage = arrayOf("/hub leave <Minigame>")
    override val permissionMessage = "Permission message"
    override val permission = "permission"

    private val plugin = Hub.plugin

    override fun onCommand(sender: CommandSender, minigame: Minigame?, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendErrorMessage("You must be a player to execute this command!")
            return true
        }

        if (args.isNotEmpty()) {
            sender.sendErrorMessage("Incorrect command usage!")
            return true
        }

        val hubPlayer = plugin.playerManger.getPlayer(sender.uniqueId)

        if (hubPlayer?.game == null) {
            sender.sendErrorMessage("You must be in a game to leave!")
            return true
        }

        plugin.gameManager.removePlayerFromMinigame(hubPlayer)
        sender.sendSuccessMessage("Left!")
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
