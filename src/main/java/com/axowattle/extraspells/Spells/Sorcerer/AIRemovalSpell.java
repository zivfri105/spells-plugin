package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Collection;

public class AIRemovalSpell extends Spell {
    private static final int effectDistance = 5;

    public AIRemovalSpell() {
        super("AI Removal", "Removes all AI from the target.", 5, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        Collection<Entity> nearbyEntities = caster.getLocation().getWorld().getNearbyEntities(caster.getLocation(),effectDistance,effectDistance,effectDistance);

        Vector direction = caster.getLocation().getDirection();
        for(Entity entity : nearbyEntities){
            Vector towardsEntity = entity.getLocation().subtract(caster.getLocation()).toVector().normalize();
            if (direction.distance(towardsEntity) > 0.3) {
                if (entity instanceof LivingEntity){
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.setAI(false);

                    livingEntity.getWorld().spawnParticle(Particle.VILLAGER_ANGRY,livingEntity.getLocation(),1,1,1,10);
                }
            }
        }
        return true;
    }
}
