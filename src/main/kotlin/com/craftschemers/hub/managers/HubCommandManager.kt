package com.craftschemers.hub.managers

import com.craftschemers.hub.commands.ICommand
import com.craftschemers.hub.commands.JoinCommand
import com.craftschemers.hub.minigame.Minigame
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class HubCommandManager : CommandExecutor {
    companion object {
        private val commands = mutableMapOf<String, ICommand>()
        init {
            fun registerCommand(command: ICommand) {
                commands[command.name] = command
            }
            registerCommand(JoinCommand())
            // ...
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("Welcome to the ${ChatColor.GOLD}Hub Plugin!")
            return true
        }
        if (!commands.containsKey(args[0])) {
            sender.sendMessage("${ChatColor.RED}Invalid command!")
            return true
        }
        commands[args[0]]?.onCommand(sender, Minigame(), label, arrayOf())
        return true
    }
}
