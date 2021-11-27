package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.ArrowEffects.ArrowHandler;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHit implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event){
        if (event.getHitBlock() != null){
            Projectile arrow = event.getEntity();
            ArrowHandler.arrowHitGround(arrow,event.getHitBlock());
        }
    }

}
