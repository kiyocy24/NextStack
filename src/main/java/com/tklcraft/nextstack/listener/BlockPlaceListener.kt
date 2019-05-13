package com.tklcraft.nextstack.listener

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

object BlockPlaceListener : Listener {
    private const val SLOT_NUM = 9

    @EventHandler
    fun onBlockPlaceListener(e : BlockPlaceEvent) {
        if (e.player.gameMode != GameMode.SURVIVAL) return
        if (e.isCancelled) return

        val inventory = e.player.inventory
        val itemStack = if( e.itemInHand.amount == 1) e.itemInHand else return

        inventory.clear(inventory.heldItemSlot)
        val inventoryIndex = inventory.first(itemStack.type).run {
            if (this < 0) return
            else this
        }

        if (inventoryIndex < SLOT_NUM) {
            inventory.heldItemSlot = inventoryIndex
        }
        else {
            val nextItemStack = inventory.getItem(inventoryIndex)
            inventory.setItem(inventory.heldItemSlot, nextItemStack)
            inventory.clear(inventoryIndex)
        }
    }

}