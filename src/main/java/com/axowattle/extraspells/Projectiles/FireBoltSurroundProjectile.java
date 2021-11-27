package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FireBoltSurroundProjectile extends SpellProjectile {

    private float effectDistance = .5f;

    public FireBoltSurroundProjectile(Player source,float angle) {
        super(source,new Vector(Math.sin(angle),0,Math.cos(angle)), 1,0,100, Particle.FLAME);
    }

    @Override
    public void onProjectileMove() {
        moveProjectileDistance(true,effectDistance);
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();
        getLocation().clone().subtract(getLocation().getDirection()).getBlock().setType(Material.FIRE);

        for(Entity entity : getLocation().getWorld().getNearbyEntities(getLocation(),effectDistance,effectDistance,effectDistance)){
            if (entity instanceof LivingEntity){
                entity.setFireTicks(100);
                ((LivingEntity) entity).damage(1);
            }
        }
    }

}
