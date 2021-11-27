package com.axowattle.extraspells.Spells;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class ClonedInventory {

    private List<ItemStack> items = new ArrayList<>();

    public ClonedInventory(PlayerInventory inventory){
        for (int i = 0;i< inventory.getSize();i++){
            if (inventory.getItem(i) == null)
                items.add(null);
            else
                items.add(inventory.getItem(i).clone());
        }
    }

    public void setInventory(PlayerInventory inventory){
        for (int i = 0;i< inventory.getSize();i++){
            inventory.setItem(i, items.get(i));
        }
    }

    public List<ItemStack> getItems() {
        return items;
    }
}
