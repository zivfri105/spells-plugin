package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import com.axowattle.extraspells.TempBlocks.TempBlocks;
import com.axowattle.extraspells.TempBlocks.TempBlocksHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class ObsidianWallProjectile extends SpellProjectile {
    public ObsidianWallProjectile(Player source) {
        super(source, 1, 0, 100, Particle.CRIT);
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();
        HashMap<Vector, Material> wall = new HashMap<>();

        for (int x = 0;x < 10; x++) {
            for (int y = 0;y < 10; y++) {
                if (getLookDir(getLocation()) == LookDir.NORTH | getLookDir(getLocation()) == LookDir.SOUTH)
                    wall.put(new Vector(x - 5,y - 5,0),Material.OBSIDIAN);
                else if (getLookDir(getLocation()) == LookDir.EAST | getLookDir(getLocation()) == LookDir.WEST)
                    wall.put(new Vector(0,y - 5,x - 5),Material.OBSIDIAN);
            }
        }
        TempBlocksHandler.addBlocks(getLocation(),wall,100);
    }

    public static LookDir getLookDir(Location location) {
        float yaw = location.getYaw();
        if (yaw < 0) {
            yaw += 360;
        }
        if (yaw >= 315 || yaw < 45) {
            return LookDir.SOUTH;
        } else if (yaw < 135) {
            return LookDir.WEST;
        } else if (yaw < 225) {
            return LookDir.NORTH;
        } else if (yaw < 315) {
            return LookDir.EAST;
        }
        return LookDir.NORTH;
    }

    private enum LookDir{
        SOUTH,WEST,NORTH,EAST
    }

}
