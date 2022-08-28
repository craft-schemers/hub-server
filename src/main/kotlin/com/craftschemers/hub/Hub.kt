package com.craftschemers.hub

import com.craftschemers.hub.managers.HubCommandManager
import com.craftschemers.hub.managers.HubPlayerManager
import org.bukkit.plugin.java.JavaPlugin

class Hub : JavaPlugin() {

    /*
    This is the main entry-point for our plugin. Let's try to abstract away most of the code into their
    respective classes, which we will reference throughout the plugin.
     */

    companion object {
        lateinit var plugin: Hub
    }
    val playerManger = HubPlayerManager()

    override fun onEnable() {
        plugin = this
        setupMinigames()
        setupCommands()
    }

    override fun onDisable() {
        // save to db
    }

    private fun setupMinigames() {
        server.pluginManager.registerEvents(Events(), this)
    }
    private fun setupCommands() {
        getCommand("hub")?.setExecutor(HubCommandManager)
    }

}
