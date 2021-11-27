package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.Spells.WandHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryOpen implements Listener {


    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
        Inventory inventory = event.getInventory();
        if (inventory.getType() == InventoryType.ENCHANTING){
            Location location = inventory.getLocation();
            if (WandHandler.isCandles(location)){
                event.setCancelled(true);
                WandHandler.openWandCraftMenu((Player) event.getPlayer());
            }
        }
    }


}
