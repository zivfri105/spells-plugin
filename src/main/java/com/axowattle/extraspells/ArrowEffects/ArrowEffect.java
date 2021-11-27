package com.axowattle.extraspells.ArrowEffects;

import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowEffect {
    private int id;
    private String name;
    private int maxStackSize;
    public ArrowEffect(String name,int maxStackSize){
        id = ArrowHandler.getId();
        this.name = name;
        this.maxStackSize = maxStackSize;
    }

    public void onArrowMove(Projectile arrow) {
    }

    public void onArrowHitGround(Projectile arrow, Block block) {
    }

    public void onArrowHitEntity(Projectile arrow,int amount, EntityDamageByEntityEvent event) {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void onArrowLaunch(Projectile arrow) {

    }

    public int getMaxStackSize() {
        return maxStackSize;
    }
}