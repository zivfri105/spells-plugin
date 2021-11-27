package com.axowattle.extraspells.Pets;

import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PetsHandler {
    private static Map<UUID, FoxPet> entities = new HashMap<>();


    public static void logout(Player player){
        for (FoxPet entity : entities.values()){
            if (entity.owner.getUniqueID() == player.getUniqueId()) {
                entity.killEntity();
                entities.remove(entity.getUniqueID());
            }
        }
    }

    public static void spawn(Player player){
        FoxPet fox = new FoxPet(player.getLocation(),player);
        WorldServer world = ((CraftWorld) player.getLocation().getWorld()).getHandle();
        world.addEntity(fox);
        entities.put(fox.getUniqueID(),fox);
    }

    public static FoxPet getFox(Entity entity){
        if (entities.containsKey(entity.getUniqueId())){
            return entities.get(entity.getUniqueId());
        }
        return null;
    }
}
