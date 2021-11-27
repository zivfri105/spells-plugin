package com.axowattle.extraspells.Spells.Cleric;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ShutterSpell extends Spell {
    private static final int effectDistance = 5;


    public ShutterSpell() {
        super("Shutter", "Deals damage to everyone around you.", 6, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        super.onSpellCast(caster);

        Collection<Entity> nearbyEntities = caster.getLocation().getWorld().getNearbyEntities(caster.getLocation(),effectDistance,effectDistance,effectDistance);

        for(Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity & entity.getUniqueId() != caster.getUniqueId()) {
                LivingEntity livingEntity = (LivingEntity) entity;

                livingEntity.damage(5,caster);

            }
        }

        caster.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE,caster.getLocation(),10,5,5,5);
        return true;
    }
}
