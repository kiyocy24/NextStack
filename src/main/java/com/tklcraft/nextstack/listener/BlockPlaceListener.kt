package com.tklcraft.nextstack.listener

import com.tklcraft.nextstack.next
import com.tklcraft.nextstack.pluginInstance
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.EquipmentSlot

object BlockPlaceListener : Listener {
    private const val SLOT_NUM = 9

    @EventHandler
    fun onBlockPlaceListener(e : BlockPlaceEvent) {
        if (!pluginInstance.config.getBoolean("uuids.${e.player.uniqueId}.enabled")) return
        if (e.player.gameMode != GameMode.SURVIVAL) return
        if (e.isCancelled) return

        val inventory = e.player.inventory
        val itemStack = if( e.itemInHand.amount == 1) e.itemInHand else return
        val hand = e.hand

        if (hand == EquipmentSlot.HAND && e.itemInHand.type != inventory.getItem(inventory.heldItemSlot)?.type) {
            return
        }

        val inventoryIndex = inventory.next(itemStack.type, inventory.heldItemSlot).run {
            if (this < 0) return
            else this
        }

        val nextItemStack = inventory.getItem(inventoryIndex)
        if (hand == EquipmentSlot.HAND) {
            if (inventoryIndex < SLOT_NUM) {
                inventory.heldItemSlot = inventoryIndex
            }
            else {
                inventory.setItem(inventory.heldItemSlot, nextItemStack)
                inventory.clear(inventoryIndex)
            }
        }
        else if (hand == EquipmentSlot.OFF_HAND) {
            inventory.setItemInOffHand(nextItemStack)
            inventory.clear(inventoryIndex)
        }
    }

}