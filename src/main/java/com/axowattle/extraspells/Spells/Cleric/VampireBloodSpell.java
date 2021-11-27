package com.axowattle.extraspells.Spells.Cleric;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VampireBloodSpell extends Spell {


    private float effectDistance = 5;
    public VampireBloodSpell() {
        super("Vampire Blood", "Steals health from all entities around you. ", 3, 30);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        for(Entity entity : caster.getLocation().getWorld().getNearbyEntities(caster.getLocation(),effectDistance,effectDistance,effectDistance)){
            if (entity instanceof LivingEntity & entity.getUniqueId() != caster.getUniqueId()){
                double entityHealth = ((LivingEntity) entity).getHealth();
                entityHealth -= 4;
                if (entityHealth < 0) entityHealth = 0;
                ((LivingEntity) entity).setHealth(entityHealth);

                rayCast(caster.getLocation().add(0,1,0),entity.getLocation().add(0,1,0),Particle.HEART,0.5f);

                double health = caster.getHealth() + 4;
                if (health > caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())
                    health = caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                caster.setHealth(health);
            }
        }
        return true;
    }


    private void rayCast(Location pos1, Location pos2, Particle particle, float distance){
        Vector dir = pos2.clone().toVector().subtract(pos1.clone().toVector()).normalize().multiply(distance);

        Location currentPos = pos1.clone();

        while (pos2.distance(currentPos) > distance * 1.5){
            currentPos.add(dir);
            currentPos.getWorld().spawnParticle(particle,currentPos,0);
        }
    }
}
