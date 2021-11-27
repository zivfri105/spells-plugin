package com.axowattle.extraspells.Projectile;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ProjectileHandler {
    private static List<SpellProjectile> projectiles = new ArrayList<>();
    private static List<SpellProjectile> remove = new ArrayList<>();
    private static int currantId = 0;

    public static void createProjectile(SpellProjectile spellProjectile){
        spellProjectile.setId(currantId);
        projectiles.add(spellProjectile);
        currantId++;
        if (currantId > 10000) currantId = 0;
    }

    public static void removeProjectile(int id){
        for (SpellProjectile projectile : projectiles){
            if (projectile.getId() == id){
                remove.add(projectile);
            }
        }
    }

    public static void activateRemoval(){
        for (SpellProjectile projectile : remove){
            projectiles.remove(projectile);
        }
    }

    public static void moveAll(){
        for(SpellProjectile projectile : projectiles){
            projectile.onProjectileMove();
        }
    }

    public static Vector correctAim(Player player, int max, float correctionDistance) {
        Location location = player.getLocation().clone();
        location.setY(location.getY()+1);

        Vector direction = location.getDirection();

        for (int i = 0;i<max;i++){
            location.add(direction);


            Entity entity = detectsEntity(location,correctionDistance,player);
            if (entity != null){
                Vector pos1 = entity.getLocation().toVector();
                Vector pos2 = player.getLocation().toVector();
                return pos1.subtract(pos2).normalize();
            }

            if (location.getBlock().getType().isSolid()){
                return direction;
            }
        }
        return direction;
    }

    public static LivingEntity detectsEntity(Location location, float triggerDistance, Entity source){
        for(Entity entity : location.getWorld().getNearbyEntities(location,triggerDistance,triggerDistance,triggerDistance)){
            if (entity instanceof LivingEntity & entity.getUniqueId() != source.getUniqueId()){
                return (LivingEntity) entity;
            }
        }
        return null;
    }

    public static LivingEntity detectsEntity(Location location, Entity source){
        for(Entity entity : location.getChunk().getEntities()){
            if (entity instanceof LivingEntity & entity.getUniqueId() != source.getUniqueId() & isWithinEntityBoundingBox(location,entity)){
                return (LivingEntity) entity;
            }
        }
        return null;
    }

    public static boolean isWithinEntityBoundingBox(Location location, Entity entity) {

        BoundingBox bb = entity.getBoundingBox();

        return (location.getX() >= bb.getMinX() && location.getY() >= bb.getMinY() && location.getZ() >= bb.getMinZ()
                && location.getX() <= bb.getMaxX() && location.getY() <= bb.getMaxY() && location.getZ() <= bb.getMaxZ());

    }
}
