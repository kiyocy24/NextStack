package com.tklcraft.nextstack

import org.bukkit.Material
import org.bukkit.inventory.PlayerInventory

fun PlayerInventory.next(material : Material, removeSlot : Int) : Int {
    val itemStacks = this.contents

    itemStacks.forEachIndexed { index, itemStack ->
        if (itemStack == null) return@forEachIndexed
        if (itemStack.type == material && index != removeSlot) {
            return index
        }
    }

    return -1
}