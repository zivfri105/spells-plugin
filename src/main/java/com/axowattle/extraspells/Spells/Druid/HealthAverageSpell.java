package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class HealthAverageSpell extends Spell {
    private static final int effectDistance = 5;

    public HealthAverageSpell() {
        super("Heath Average", "Averages the health of everyone around the druid including the spellcaster", 4, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        double average = 0;
        int entityAmount = 0;

        Collection<Entity> nearbyEntities = caster.getLocation().getWorld().getNearbyEntities(caster.getLocation(),effectDistance,effectDistance,effectDistance);

        for(Entity entity : nearbyEntities){
            if (entity instanceof LivingEntity){
                LivingEntity livingEntity = (LivingEntity) entity;

                average += livingEntity.getHealth();
                entityAmount ++;

            }
        }

        average /= entityAmount;

        for(Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;

                livingEntity.setHealth(average);

                livingEntity.getWorld().spawnParticle(Particle.HEART,livingEntity.getLocation(),1,1,1,10);

            }
        }
        return true;
    }
}
