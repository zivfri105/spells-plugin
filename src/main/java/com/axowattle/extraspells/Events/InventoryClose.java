package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.SpellHandler;
import com.axowattle.extraspells.Spells.WandHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClose implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player);
        Inventory inventory = event.getInventory();
        if (PlayerData.getPlayerData(player).isInInventory(1)){
            for(int i = 0;i< playerData.selectedSpells.length;i++){
                playerData.selectedSpells[i] = SpellHandler.getSpellCount(player,SpellHandler.getSpell(playerData.playerClass,inventory.getItem(SpellHandler.getSelectedSpellSlots()[i])));
            }
        }
        if (playerData!= null)
            playerData.resetInventory();
    }


}
