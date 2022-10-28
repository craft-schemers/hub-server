package com.craftschemers.hub.commands.set

import com.craftschemers.hub.commands.ICommand
import com.craftschemers.hub.minigame.Minigame
import org.bukkit.command.CommandSender

object SetLobbyCommand : ICommand {

    override val name = "lobby"
    override val aliases = null
    override val canBeConsole = false
    override val description = "Sets the lobby position of a Minigame to where you are standing"
    override val parameters = null
    override val usage = arrayOf("/hub set <Minigame> lobby")
    override val permissionMessage = "Permission message"
    override val permission = "permission"

    override fun onCommand(sender: CommandSender, minigame: Minigame?, label: String, args: Array<String>): Boolean {
        TODO("Not yet implemented")
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
