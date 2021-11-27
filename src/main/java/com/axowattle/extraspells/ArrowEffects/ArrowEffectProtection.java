package com.axowattle.extraspells.ArrowEffects;

import com.sun.org.apache.bcel.internal.generic.FLOAD;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class ArrowEffectProtection extends ArrowEffect{
    private HashMap<Projectile, Float> projectile = new HashMap<>();

    float distance = 4;
    public ArrowEffectProtection() {
        super("ProtectionArrow",1);
    }

    @Override
    public void onArrowLaunch(Projectile arrow) {
        arrow.setGravity(false);
    }

    @Override
    public void onArrowMove(Projectile arrow) {
        if (!projectile.containsKey(arrow)) projectile.put(arrow,0f);
        Vector currentPos = arrow.getLocation().toVector();
        Vector playerPos = ((Entity) arrow.getShooter()).getLocation().toVector();
        Vector requiredPos = playerPos.clone().add(new Vector(Math.sin(projectile.get(arrow)),0.5,Math.cos(projectile.get(arrow))).multiply(distance));
        Vector dir = requiredPos.clone().subtract(currentPos);
        dir.normalize();
        arrow.setVelocity(dir);
        arrow.getLocation().setDirection(dir);
        //arrow.getWorld().spawnParticle(Particle.BARRIER,arrow.getLocation().clone().zero().add(requiredPos),0);
        projectile.put(arrow,projectile.get(arrow) + 0.3f);
    }

    @Override
    public void onArrowHitEntity(Projectile arrow, int amount, EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity & arrow.getShooter() instanceof Entity){
            event.setDamage(event.getDamage() + 4);
        }
    }
}
