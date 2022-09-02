package com.craftschemers.hub.commands.set

import com.craftschemers.hub.Hub
import com.craftschemers.hub.commands.ICommand
import com.craftschemers.hub.minigame.Minigame
import com.craftschemers.hub.minigame.getGameTypeFromName
import com.craftschemers.hub.minigame.sendErrorMessage
import org.bukkit.command.CommandSender

object SetCommand : ICommand {

    private val setCommands = mapOf(
        SetLobbyCommand.name to SetLobbyCommand,
    )

    private val plugin = Hub.plugin

    override val name = "set"
    override val aliases = null
    override val canBeConsole = true
    override val description = "Modifies a Minigame using special parameters for each game type."
    override val parameters = null
    override val usage = arrayOf("/hub set <Minigame> <Parameters>...")
    override val permissionMessage = "Permission message"
    override val permission = "permission"


    override fun onCommand(sender: CommandSender, minigame: Minigame?, label: String, args: Array<String>): Boolean {
        if (args.size <= 1) {
            sender.sendErrorMessage("Incorrect command usage!")
            return true
        }

        val gameType = getGameTypeFromName(args[0])
        setCommands[args[1]]?.onCommand(sender, minigame, label, args)
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
