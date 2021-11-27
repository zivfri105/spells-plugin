package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.CustomMobs.Thief;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Random;

public class BlockPlace implements Listener {
    private static Random random = new Random();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (event.getBlockPlaced().getType() == Material.GOLD_BLOCK & random.nextInt(20) == 0){
            Player player = event.getPlayer();
            Thief thief = new Thief(player.getLocation(),player);
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
            world.addEntity(thief);
        }
    }
}
