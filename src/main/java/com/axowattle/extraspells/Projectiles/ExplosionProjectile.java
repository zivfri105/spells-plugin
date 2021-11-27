package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ExplosionProjectile extends SpellProjectile {
    private float effectDistance = 7;
    private float angle = 0;
    public ExplosionProjectile(Player source) {
        super(source, 1,0,100, Particle.ENCHANTMENT_TABLE);
    }

    @Override
    public void onProjectileMove() {
        moveProjectileDistance(false,1);

        getLocation().getWorld().spawnParticle(getParticle(),getSide(angle,1),0);
        getLocation().getWorld().spawnParticle(getParticle(),getSide(angle + 180,1),0);

        angle += 6;
    }



    public Location getSide(float angle,float distance){


        Vector direction = getLocation().getDirection();
        Vector position = getLocation().toVector();



        Vector vec1 = new Vector();
        Vector vec2 = new Vector();

        if (direction.getX() != 0){
            vec1.setZ(0);
            vec1.setY(1);
            vec1.setX((-direction.getY()) / direction.getX());
        }else if(direction.getY() != 0){
            vec1.setZ(0);
            vec1.setX(1);
            vec1.setY((-direction.getX()) / direction.getY());
        }else if(direction.getZ() != 0){
            vec1.setX(1);
            vec1.setY(0);
            vec1.setZ(0);

            vec2.setX(0);
            vec2.setY(1);
            vec2.setZ(0);
        }

        vec1.normalize();

        vec2.setX(position.getX() + vec1.getX() * distance * Math.cos(angle) + vec2.getX() * distance * Math.sin(angle));
        vec2.setY(position.getY() + vec1.getY() * distance * Math.sin(angle) + vec2.getY() * distance * Math.cos(angle));
        vec2.setZ(position.getZ() + vec1.getZ() * distance * Math.cos(angle) + vec2.getZ() * distance * Math.sin(angle));

        Location newLoc = getLocation().clone();
        newLoc.setX(vec2.getX());
        newLoc.setY(vec2.getY());
        newLoc.setZ(vec2.getZ());

        return newLoc;
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();

        getLocation().getWorld().createExplosion(getLocation(),2);
    }
}
