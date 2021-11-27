package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CloudKillProjectile extends SpellProjectile {
    public CloudKillProjectile(Player source) {
        super(source, 1, 0, 100, Particle.SQUID_INK);
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();

        for (LivingEntity entity : getAllEntities(getLocation(),5)){
            if (entity.getUniqueId() != getSource().getUniqueId())
                entity.damage(20,getSource());
        }

        getLocation().getWorld().spawnParticle(Particle.SQUID_INK,getLocation().subtract(getLocation().getDirection().multiply(2)),300,5,5,5);
        getLocation().getWorld().createExplosion(getLocation(),1.5f);
    }
}
