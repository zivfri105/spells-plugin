package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.ArrowEffects.ArrowHandler;
import com.axowattle.extraspells.CustomMobs.ZombieLeader;
import com.axowattle.extraspells.CustomMobs.ZombieMember;
import com.axowattle.extraspells.Pets.FoxPet;
import com.axowattle.extraspells.Spells.ArceaneArcher.DamageReduceSpell;
import com.axowattle.extraspells.Spells.Cleric.WitherThornsSpell;
import com.axowattle.extraspells.Spells.Druid.KnockbackAttackSpell;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EntityDamageByEntity implements Listener {

    private static final double knockbackAmount = 10;

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        WitherThornsSpell.onEntityDamage(event);
        if(event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (event.getDamager() instanceof Projectile) {
                Projectile arrow = (Arrow) event.getDamager();
                ArrowHandler.arrowHitEntity(arrow, event);
            }
        }

        if (event.getDamager() instanceof Player & event.getEntity() instanceof LivingEntity){
            Player player = (Player) event.getDamager();
            if (KnockbackAttackSpell.isKnockback(player)){
                LivingEntity entity = (LivingEntity) event.getEntity();
                entity.setVelocity(entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize().multiply(knockbackAmount));
            }
        }
    }

}
