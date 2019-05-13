package com.tklcraft.nextstack

import org.bukkit.Bukkit.getPluginManager

val pluginInstance : NextStackPlugin by lazy {
    val instance = getPluginManager().getPlugin("NextStack")
    requireNotNull(instance) { warning("Plugin instance is null.") }
    return@lazy instance as NextStackPlugin
}
internal fun info(message : String) = pluginInstance.logger.info(message)
internal fun warning(message : String) = pluginInstance.logger.warning(message)
