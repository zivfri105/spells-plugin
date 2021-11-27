package com.axowattle.extraspells.Projectile;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SpellProjectile {
    private int id;
    private Location location;
    private float speed;
    private Particle particle;
    private Player source;
    private int maxDistance;
    private int distance;
    private Vector dir;
    private float correctionDistance;

    public SpellProjectile( Player source, float speed,float correctionDistance,int maxDistance, Particle particle){
        this.location = source.getLocation().clone().add(0,1,0);
        this.source = source;
        this.speed = speed;
        this.particle = particle;
        this.maxDistance = maxDistance;
        this.correctionDistance = correctionDistance;
        this.dir = ProjectileHandler.correctAim(source,100,correctionDistance);

    }

    public SpellProjectile(Player source, Vector dir, float speed,float correctionDistance,int maxDistance, Particle particle){
        this.location = source.getLocation().clone().add(0,1,0);
        this.speed = speed;
        this.source = source;
        this.particle = particle;
        this.maxDistance = maxDistance;
        this.correctionDistance = correctionDistance;
        this.dir = dir;
    }


    public void onProjectileMove(){
        moveProjectileDefault(true);
    }

    public final void moveProjectileDefault(boolean show){
        location.add(dir.multiply(speed));

        if(show) location.getWorld().spawnParticle(particle,location,0);

        if (ProjectileHandler.detectsEntity(location,source) != null | location.getBlock().getType().isSolid() | distance > maxDistance)
            onProjectileHit();
        distance++;
    }

    public final void moveProjectileDistance(boolean show,float detectDistance){
        location.add(dir.multiply(speed));

        if(show) location.getWorld().spawnParticle(particle,location,0);

        if (ProjectileHandler.detectsEntity(location,detectDistance,source) != null | location.getBlock().getType().isSolid() | distance > maxDistance)
            onProjectileHit();
        distance++;
    }



    public void onProjectileHit(){
        ProjectileHandler.removeProjectile(id);
    }

    public Player getSource() {
        return source;
    }

    public Location getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Particle getParticle() {
        return particle;
    }

    @Override
    public String toString() {
        return "SpellProjectile{" +
                "id=" + id +
                ", location=" + location +
                ", speed=" + speed +
                ", particle=" + particle +
                ", source=" + source +
                ", maxDistance=" + maxDistance +
                ", distance=" + distance +
                ", dir=" + dir +
                '}';
    }

    public final List<LivingEntity> getAllEntities(Location location, double radius){
        List<LivingEntity> entities = new ArrayList<>();
        for(Entity entity : location.getWorld().getNearbyEntities(location,radius,radius,radius)){
            if (entity instanceof LivingEntity & location.distance(entity.getLocation()) < radius){
                entities.add((LivingEntity) entity);
            }
        }
        return entities;
    }
}
