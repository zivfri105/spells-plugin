package com.axowattle.extraspells.Handlers;

import com.axowattle.extraspells.ExtraSpells;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Sphere {
    final static double cubeMidpoint = .375;

    private final List<ArmorStand> armorStands;

    private final Location location;
    private final double radius;
    private final int samples;
    private final double speed;

    public Sphere(Location location,ItemStack headItem,double radius,int samples,double speed){
        this.samples = samples;
        this.radius = radius;
        this.armorStands = new ArrayList<>();
        this.location = location;
        this.speed = speed;

        generateSphere(headItem);
    }

    private void generateSphere(ItemStack headItem){
        for (Vector point : GenerateFibonacciSphere(samples,radius)){
            Vector direction = point.clone().normalize();
            EulerAngle lookDir = directionToEuler(direction.clone());


            direction.multiply(Math.sin(lookDir.getX()) * cubeMidpoint);
            direction.setY(Math.cos(lookDir.getX()) * cubeMidpoint);

            Location loc = location.clone().add(point).subtract(direction);
            loc.setYaw(0);
            loc.setPitch(0);

            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            armorStand.setHeadPose(lookDir);
            armorStand.setGravity(false);
            armorStand.setInvisible(true);
            armorStand.getEquipment().setHelmet(headItem);
            armorStands.add(armorStand);
        }
    }

    public void onSphereMove(){
        Vector dir = location.getDirection().clone();
        location.add(dir.multiply(speed));

        updateSphereLocation();
    }

    public final List<LivingEntity> getAllEntities(){
        List<LivingEntity> entities = new ArrayList<>();
        for(Entity entity : location.getWorld().getNearbyEntities(location,radius,radius,radius)){
            if (entity instanceof LivingEntity & location.distance(entity.getLocation()) < radius){
                entities.add((LivingEntity) entity);
            }
        }
        return entities;
    }

    private void updateSphereLocation(){
        List<Vector> points = GenerateFibonacciSphere(samples,radius);
        for (int i = 0; i< points.size();i++){
            Vector direction = points.get(i).clone().normalize();
            EulerAngle lookDir = directionToEuler(direction.clone());

            direction.multiply(Math.sin(lookDir.getX()) * cubeMidpoint);
            direction.setY(Math.cos(lookDir.getX()) * cubeMidpoint);

            Location loc = location.clone().add(points.get(i)).subtract(direction);
            loc.setYaw(0);
            loc.setPitch(0);

            armorStands.get(i).teleport(loc);

            if (armorStands.get(i).getLocation().add(0,1,0).getBlock().getType().isSolid()){
                armorStands.get(i).setHealth(0);
            }
        }
    }

    public int getAmountAlive(){
        int alive =0;
        for (ArmorStand armorStand : armorStands){
            if (armorStand.isDead()) alive++;
        }
        return alive;
    }

    private static List<Vector> GenerateFibonacciSphere(int samples, double radius)
    {
        List<Vector> locations = new ArrayList<>();

        for (int i = 0; i < samples; i++)
        {
            double theta = 2 * Math.PI * i / 1.61803398875f; // golden ratio: 1.61803398875f
            double phi = Math.acos(1 - 2 * (i + 0.5) / samples);
            Vector pointOnSphere = new Vector(Math.cos(theta) * Math.sin(phi),
                    Math.sin(theta) * Math.sin(phi), Math.cos(phi)).multiply(radius);
            locations.add(pointOnSphere);
        }
        return locations;
    }

    private EulerAngle directionToEuler(Vector dir) {
        double xzLength = Math.sqrt(dir.getX()*dir.getX() + dir.getZ()*dir.getZ());
        double pitch = Math.atan2(xzLength, dir.getY()) - Math.PI / 2;
        double yaw = -Math.atan2(dir.getX(), dir.getZ());
        return new EulerAngle(pitch, yaw, 0);
    }

    public Location getLocation() {
        return location;
    }
}
