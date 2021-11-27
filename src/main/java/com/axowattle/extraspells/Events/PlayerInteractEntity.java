package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.Pets.FoxPet;
import com.axowattle.extraspells.Pets.PetsHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntity implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
        FoxPet pet = PetsHandler.getFox(event.getRightClicked());
        if(pet != null) pet.toggleSitting();
    }

}
