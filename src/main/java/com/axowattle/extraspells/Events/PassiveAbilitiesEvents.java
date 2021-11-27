package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.PassiveAbilities.PassiveAbility;
import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class PassiveAbilitiesEvents implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if (PlayerData.getPlayerData(player).playerClass == null) return;
        for(PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)){
            passiveAbility.onPlayerMove(event);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (PlayerData.getPlayerData(player).playerClass == null) return;
        for(PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)){
            passiveAbility.onPlayerInteract(event);
        }
    }

    @EventHandler
    public void onPlayerFlyToggle(PlayerToggleFlightEvent event){
        Player player = event.getPlayer();
        if (PlayerData.getPlayerData(player).playerClass == null) return;
        for(PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)){
            passiveAbility.onPlayerFlyToggle(event);
        }
    }

    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (PlayerData.getPlayerData(player).playerClass == null) return;
            for (PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)) {
                passiveAbility.onPlayerDamaged(event);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        if (PlayerData.getPlayerData(player).playerClass == null) return;
        for (PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)) {
            passiveAbility.onPlayerDeath(event);
        }
    }

    @EventHandler
    public void onPlayerDamagedByEntity(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (PlayerData.getPlayerData(player).playerClass == null) return;
            for (PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)) {
                passiveAbility.onPlayerDamagedByEntity(event);
            }
        }
    }

    @EventHandler
    public void onEntityDamagedByPlayer(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (PlayerData.getPlayerData(player).playerClass == null) return;
            for (PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)) {
                passiveAbility.onEntityDamagedByPlayer(event);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (PlayerData.getPlayerData(player).playerClass == null) return;
        for(PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)){
            passiveAbility.onPlayerJoin(event);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (PlayerData.getPlayerData(player).playerClass == null) return;
        for(PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)){
            passiveAbility.onPlayerQuit(event);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        if (PlayerData.getPlayerData(player).playerClass == null) return;
        for(PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)){
            passiveAbility.onPlayerDropItem(event);
        }
    }

}
