package com.craftschemers.hub.commands

import com.craftschemers.hub.minigame.Minigame
import org.bukkit.command.CommandSender

object LeaveCommand : ICommand {
    override val name: String
        get() = TODO("Not yet implemented")
    override val aliases: Array<String>?
        get() = TODO("Not yet implemented")
    override val canBeConsole: Boolean
        get() = TODO("Not yet implemented")
    override val description: String
        get() = TODO("Not yet implemented")
    override val parameters: Array<String>?
        get() = TODO("Not yet implemented")
    override val usage: Array<String>
        get() = TODO("Not yet implemented")
    override val permissionMessage: String
        get() = TODO("Not yet implemented")
    override val permission: String
        get() = TODO("Not yet implemented")

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
