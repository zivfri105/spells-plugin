package com.axowattle.extraspells.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class FireballHit implements Listener {

    private static final int fireHitSize = 20;

    @EventHandler
    public void onFireballHit(EntityExplodeEvent event){
        if (event.getEntity().getCustomName().equals("gfgvhjGUYfguiT58TiuU()*T687$&^GuiHB")){
            event.blockList().clear();
            for (int x=0;x< fireHitSize;x++){
                for (int y=0;y< fireHitSize;y++){
                    for (int z=0;z< fireHitSize;z++){
                        Block block = event.getLocation().clone().add(fireHitSize/2 + x,fireHitSize/2 + y,fireHitSize/2 + z).getBlock();
                        block.setType(Material.FIRE);
                        event.blockList().add(block);
                    }
                }
            }
        }
    }
}
