package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LaunchProjectile extends SpellProjectile {
    public LaunchProjectile(Player source) {
        super(source, 1, 0, 100, Particle.BUBBLE_POP);
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();

        for(Entity entity : getLocation().getWorld().getNearbyEntities(getLocation(),1,2,1)){
            if (entity instanceof LivingEntity){
                entity.setVelocity(new Vector(0,10,0));
            }
        }
    }
}
