package com.tklcraft.nextstack.listener

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.EquipmentSlot

object BlockPlaceListener : Listener {
    private const val SLOT_NUM = 9

    @EventHandler
    fun onBlockPlaceListener(e : BlockPlaceEvent) {
        if (e.player.gameMode != GameMode.SURVIVAL) return
        if (e.isCancelled) return

        val inventory = e.player.inventory
        val itemStack = if( e.itemInHand.amount == 1) e.itemInHand else return
        val hand = e.hand

        if (hand == EquipmentSlot.HAND) {
            inventory.clear(inventory.heldItemSlot)
        }
        val inventoryIndex = inventory.first(itemStack.type).run {
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