package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.Npcs.NPCHandler;
import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (PlayerData.getPlayerData(player) == null){
            SpellHandler.addNewPlayer(player);
        }else if(PlayerData.getPlayerData(player).playerClass == null){
            SpellHandler.addNewPlayer(player);
        }

        NPCHandler.onPlayerJoin(event);
    }
}
