package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

public class AnvilRainProjectile extends SpellProjectile {
    private static final int platformSize = 10;


    public AnvilRainProjectile(Player source) {
        super(source, 1, 0, 100, Particle.CRIT);
    }

    @Override
    public void onProjectileHit() {
        Location location = getLocation().add(0,255,0);
        for (int x = 0;x < platformSize;x++){
            for (int z = 0;z < platformSize;z++){
                Location anvilSpawn = location.add(platformSize/2 + x,0, platformSize/2 + z);
                FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(anvilSpawn, Material.ANVIL.createBlockData());
                fallingBlock.setHurtEntities(true);
                fallingBlock.setDropItem(false);
            }
        }
    }
}
