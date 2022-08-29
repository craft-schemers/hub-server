package com.craftschemers.hub.commands

import com.craftschemers.hub.minigame.Minigame
import org.bukkit.command.CommandSender

interface ICommand {

    val name: String
    val aliases: Array<String>?
    val canBeConsole: Boolean
    val description: String
    val parameters: Array<String>?
    val usage: Array<String>
    val permissionMessage: String
    val permission: String

    fun onCommand(sender: CommandSender, minigame: Minigame?, label: String, args: Array<String>): Boolean

    fun onTabComplete(
        sender: CommandSender,
        minigame: Minigame,
        alias: String,
        args: Array<String>
    ): List<String>
}
