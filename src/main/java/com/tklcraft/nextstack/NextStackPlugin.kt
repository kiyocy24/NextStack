package com.tklcraft.nextstack

import com.tklcraft.nextstack.command.NextStackCommand
import com.tklcraft.nextstack.listener.BlockPlaceListener
import com.tklcraft.nextstack.listener.LoginListener
import org.bukkit.plugin.java.JavaPlugin

class NextStackPlugin : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        // Set listener
        server.pluginManager.registerEvents(BlockPlaceListener, pluginInstance)
        server.pluginManager.registerEvents(LoginListener, pluginInstance)
        // Set command
        getCommand("nextstack")?.setExecutor(NextStackCommand)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
