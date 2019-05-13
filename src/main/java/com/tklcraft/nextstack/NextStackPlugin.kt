package com.tklcraft.nextstack

import org.bukkit.plugin.java.JavaPlugin

class NextStackPlugin : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(BlockPlaceListener, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
