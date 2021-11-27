package com.axowattle.extraspells.PassiveAbilities;

import com.axowattle.extraspells.Events.EntityDamageByEntity;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class PassiveAbility {

    private final String name;
    private final String description;
    private final int minLevel;

    public PassiveAbility(String name,String description,int minLevel){
        this.name = name;
        this.description = description;
        this.minLevel = minLevel;
    }

    public final int getMinLevel() {
        return minLevel;
    }

    public final String getDescription() {
        return description;
    }

    public final String getName() {
        return name;
    }

    public void onPlayerMove(PlayerMoveEvent event){

    }

    public void onPlayerInteract(PlayerInteractEvent event){

    }

    public void onPlayerFlyToggle(PlayerToggleFlightEvent event){

    }

    public void onPlayerDamaged(EntityDamageEvent event){

    }

    public void onPlayerDamagedByEntity(EntityDamageByEntityEvent event){

    }

    public void onEntityDamagedByPlayer(EntityDamageByEntityEvent event){

    }

    public void onPlayerJoin(PlayerJoinEvent event){

    }

    public void onPlayerQuit(PlayerQuitEvent event){

    }

    public void onTick(Player player){

    }

    public void onPlayerDeath(PlayerDeathEvent event){

    }

    public void onDisable(Player player){

    }

    public void onPlayerDropItem(PlayerDropItemEvent event){

    }
}
