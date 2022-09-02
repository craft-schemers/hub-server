package com.craftschemers.hub.managers

import com.craftschemers.hub.commands.JoinCommand
import com.craftschemers.hub.commands.LeaveCommand
import com.craftschemers.hub.commands.PrintPlayersInGameCommand
import com.craftschemers.hub.commands.set.SetCommand
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class HubCommandManager : CommandExecutor {

    private val commands = mapOf(
        JoinCommand.name to JoinCommand,
        LeaveCommand.name to LeaveCommand,
        SetCommand.name to SetCommand,
        PrintPlayersInGameCommand.name to PrintPlayersInGameCommand
    )

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("Welcome to the ${ChatColor.GOLD}Hub Plugin!")
            return true
        }
        if (args[0] !in commands) {
            sender.sendMessage("${ChatColor.RED}Invalid command!")
            return true
        }
        commands[args[0]]?.onCommand(sender, null, label, args.copyOfRange(1, args.size))
        return true
    }
}
