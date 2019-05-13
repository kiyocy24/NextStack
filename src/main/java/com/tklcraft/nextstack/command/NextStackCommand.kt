package com.tklcraft.nextstack.command

import com.tklcraft.nextstack.pluginInstance
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

object NextStackCommand : CommandExecutor, TabCompleter {
    private const val ON = "on"
    private const val OFF = "off"
    private const val ENABLED_MESSAGE = "NextStack plugin is enabled."
    private const val DISABLE_MESSAGE = "NextStack plugin is disable."

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!command.name.equals("nextstack", ignoreCase = true)) return false
        if (sender !is Player) return false

        val player : Player = sender
        val basePath = "uuids.${player.uniqueId}"
        val enabled = pluginInstance.config.getBoolean("$basePath.enabled")

        if (args.isEmpty()) {
            if(enabled) player.sendMessage(ENABLED_MESSAGE)
            else player.sendMessage(DISABLE_MESSAGE)
            return true
        }
        else {
            when(args[0]) {
                ON -> {
                    pluginInstance.config.set("$basePath.enabled", true)
                    pluginInstance.saveConfig()
                    player.sendMessage(ENABLED_MESSAGE)
                }
                OFF -> {
                    pluginInstance.config.set("$basePath.enabled", false)
                    pluginInstance.saveConfig()
                    player.sendMessage(DISABLE_MESSAGE)
                }
                else ->
                    return false
            }
        }
        return true
    }


    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        val tabList = mutableListOf<String>()
        if (sender.hasPermission("nt")) {
            tabList.add(ON)
            tabList.add(OFF)
        }
        return tabList
    }
}