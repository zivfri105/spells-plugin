package com.axowattle.extraspells.ArrowEffects;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArrowHealthSteal extends ArrowEffect{
    public ArrowHealthSteal() {
        super("ArrowHealthSteal",5);
    }

    @Override
    public void onArrowHitEntity(Projectile arrow, int amount, EntityDamageByEntityEvent event) {
        if (event.getEntity().getType().isAlive()){
            Player caster = (Player) arrow.getShooter();
            double health = caster.getHealth() + event.getFinalDamage() / (8 - amount);
            if (health > caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())
                health = caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            caster.setHealth(health);
        }
    }
}
