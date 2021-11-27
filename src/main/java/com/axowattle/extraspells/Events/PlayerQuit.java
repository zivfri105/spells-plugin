package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.Pets.PetsHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        PetsHandler.logout(event.getPlayer());
    }
}
