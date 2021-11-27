package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LightningProjectile extends SpellProjectile {
    private float effectDistance = 3;
    private float angle = 0;
    public LightningProjectile(Player source) {
        super(source, 1,0,100, Particle.LIGHT);
    }

    @Override
    public void onProjectileMove() {
        moveProjectileDistance(true,effectDistance);

        angle += 6;
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();

        for(Entity entity : getLocation().getWorld().getNearbyEntities(getLocation(),effectDistance,effectDistance,effectDistance)){
            if (entity instanceof LivingEntity & entity.getUniqueId() != getSource().getUniqueId()){
                entity.getWorld().strikeLightning(entity.getLocation());
            }
        }
    }
}
