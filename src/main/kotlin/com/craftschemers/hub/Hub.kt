package com.craftschemers.hub

import com.craftschemers.hub.managers.HubCommandManager
import com.craftschemers.hub.managers.HubMinigameManager
import com.craftschemers.hub.managers.HubPlayerManager
import org.bukkit.Bukkit
import org.bukkit.WorldCreator
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class Hub : JavaPlugin {

    /*
    This is the main entry-point for our plugin. Let's try to abstract away most of the code into their
    respective classes, which we will reference throughout the plugin.
     */

    constructor() : super()
    constructor(
        loader: JavaPluginLoader,
        description: PluginDescriptionFile,
        dataFolder: File,
        file: File
    ) : super(
        loader, description, dataFolder, file
    )

    companion object {
        lateinit var plugin: Hub
    }

    lateinit var playerManger: HubPlayerManager
    lateinit var gameManager: HubMinigameManager
    private lateinit var commandManager: HubCommandManager

    override fun onEnable() {
        plugin = this
        setupWorlds()
        playerManger = HubPlayerManager()
        gameManager = HubMinigameManager()
        commandManager = HubCommandManager()
        setupMinigames()
        setupCommands()
    }

    override fun onDisable() {
        // save to db
    }

    private fun setupWorlds() {
        // TODO read from config.yml instead
        Bukkit.getServer().createWorld(WorldCreator("oitq-lobby1"))
    }

    private fun setupMinigames() {
        server.pluginManager.registerEvents(Events(), this)
    }

    private fun setupCommands() {
        getCommand("hub")?.setExecutor(commandManager)
    }

}
