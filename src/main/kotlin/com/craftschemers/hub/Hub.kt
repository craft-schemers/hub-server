package com.craftschemers.hub

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
    }

    override fun onDisable() {}

    private fun setupMinigames() {
        this.server.pluginManager.registerEvents(Events(), this)
    }

}
