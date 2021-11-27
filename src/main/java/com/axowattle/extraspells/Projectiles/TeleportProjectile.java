package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class TeleportProjectile extends SpellProjectile {
    public TeleportProjectile(Player source) {
        super(source, 1, 0, 20, Particle.SQUID_INK);
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();
        getSource().teleport(getLocation().clone().subtract(getLocation().getDirection().multiply(2)));
    }
}
