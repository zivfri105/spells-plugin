package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import com.axowattle.extraspells.TempBlocks.TempBlocksHandler;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class FreezeProjectile extends SpellProjectile {
    public FreezeProjectile(Player source) {
        super(source, 1, 0, 100, Particle.SNOWFLAKE);
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();

        Map<Vector, Material> blocks = new HashMap<>(); // {(0,0,0):ice,{0,1,0}:air}

        for (int x = 0;x<3;x++){
            for (int z = 0;z<3;z++){
                for (int y = 0;y<4;y++){
                    blocks.put(new Vector(x-1,y-1,z-1),Material.ICE);
                }
            }
        }


        TempBlocksHandler.addBlocks(getLocation(),blocks,400L);

        for(Entity entity : getLocation().getWorld().getNearbyEntities(getLocation(),1,2,1)){
            if (entity instanceof LivingEntity & entity.getUniqueId() != getSource().getUniqueId()){
                entity.setFreezeTicks(400);
            }
        }

    }
}
