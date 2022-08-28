package com.craftschemers.hub.commands

import com.craftschemers.hub.minigame.Minigame
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class JoinCommand: ICommand {

    override val name: String = "join"
    override val aliases: Array<String>? = null
    override val canBeConsole: Boolean = false
    override val description: String = "Use this command to join a minigame"
    override val parameters: Array<String>? = null
    override val usage: Array<String> = arrayOf("/hub join <Minigame>")
    override val permissionMessage: String = "Permission message"
    override val permission: String = "permission"

    override fun onCommand(sender: CommandSender, minigame: Minigame, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}You must be a player to execute this command!")
            return true
        }
        sender.sendMessage("${ChatColor.GOLD}Joined!")
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
