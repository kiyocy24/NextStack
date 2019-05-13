package com.tklcraft.nextstack.listener

import com.tklcraft.nextstack.pluginInstance
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

object LoginListener : Listener {
    @EventHandler
    fun onPlayerLoginEvent(e : PlayerLoginEvent) {
        val player = e.player
        val baseBath = "uuids.${player.uniqueId}"
        if (pluginInstance.config.contains(baseBath)) return
        pluginInstance.config.set("$baseBath.name", player.name)
        pluginInstance.config.set("$baseBath.enabled", true)
        pluginInstance.saveConfig()
    }
}