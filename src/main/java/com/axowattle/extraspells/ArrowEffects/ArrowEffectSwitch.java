package com.axowattle.extraspells.ArrowEffects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArrowEffectSwitch extends ArrowEffect{
    public ArrowEffectSwitch() {
        super("SwitchEffect",1);
    }

    @Override
    public void onArrowHitGround(Projectile arrow, Block block) {
        Player player = (Player) arrow.getShooter();
        Location loc = player.getLocation().clone();
        player.teleport(block.getLocation());
        loc.getBlock().setType(block.getType());
        loc.getBlock().setBlockData(block.getBlockData());
        block.setType(Material.AIR);
    }

    @Override
    public void onArrowHitEntity(Projectile arrow, int amount, EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (arrow.getShooter() instanceof Player){
            Player player = (Player) arrow.getShooter();
            Location loc = player.getLocation().clone();
            player.teleport(entity.getLocation());
            entity.teleport(loc);
        }
    }
}
