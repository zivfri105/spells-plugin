package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.CustomMobs.ZombieLeader;
import com.axowattle.extraspells.CustomMobs.ZombieMember;
import com.axowattle.extraspells.Spells.ArceaneArcher.DamageReduceSpell;
import com.axowattle.extraspells.Spells.Sorcerer.InvisibilitySpell;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamaged implements Listener {


    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            InvisibilitySpell.removeInvisibility(player);
            DamageReduceSpell.onEntityDamage(event);

        }
    }
}
