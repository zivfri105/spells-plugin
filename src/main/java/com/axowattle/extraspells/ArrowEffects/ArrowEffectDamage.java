package com.axowattle.extraspells.ArrowEffects;

import com.axowattle.extraspells.ExtraSpells;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowEffectDamage extends ArrowEffect{
    public ArrowEffectDamage() {
        super("ArrowDamage",2);
    }

    @Override
    public void onArrowHitEntity(Projectile arrow, int amount, EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity & arrow.getShooter() instanceof Player){
            event.setDamage(event.getDamage() + 8 * amount);
        }
    }
}
