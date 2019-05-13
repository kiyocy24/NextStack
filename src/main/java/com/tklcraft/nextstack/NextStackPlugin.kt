package com.tklcraft.nextstack

import com.tklcraft.nextstack.command.NextStackCommand
import com.tklcraft.nextstack.listener.BlockPlaceListener
import com.tklcraft.nextstack.listener.LoginListener
import org.bukkit.plugin.java.JavaPlugin

class NextStackPlugin : JavaPlugin() {

    override fun onEnable() {
        // Save default config
        saveDefaultConfig()
        info("Save default config.")

        // Set listener
        server.pluginManager.registerEvents(BlockPlaceListener, pluginInstance)
        server.pluginManager.registerEvents(LoginListener, pluginInstance)
        info("Set listener.")

        // Set command
        getCommand("nextstack")?.setExecutor(NextStackCommand)
        info("Set command.")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
