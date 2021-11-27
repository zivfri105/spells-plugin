package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.CustomMobs.ZombieLeader;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class EntitySpawn implements Listener {
    public static Random random = new Random();
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){
        if (random.nextInt(200) == 0 & event.getEntityType() == EntityType.ZOMBIE){
            ZombieLeader leader = new ZombieLeader(event.getLocation());
            WorldServer world = ((CraftWorld) event.getLocation().getWorld()).getHandle();
            world.addEntity(leader);
            leader.spawnMembers(6,10);
            event.setCancelled(true);
        }
    }
}
