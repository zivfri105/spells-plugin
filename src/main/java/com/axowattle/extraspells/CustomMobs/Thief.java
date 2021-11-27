package com.axowattle.extraspells.CustomMobs;

import com.axowattle.extraspells.Spells.SpellHandler;
import com.axowattle.extraspells.Spells.WandHandler;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.level.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Thief extends EntityVillager {
    private static Random random = new Random();
    private ArrayList items;

    public Thief(Location loc, Player player) {
        super(EntityTypes.aV, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(),loc.getY(),loc.getZ());

        this.setCustomName(new ChatComponentText(ChatColor.GOLD + "" + ChatColor.BOLD + "Thief"));
        this.setCustomNameVisible(true);

        this.setHealth(100);

        items = new ArrayList<>();

        PlayerInventory inventory = player.getInventory();

        for (int i = 0; i< inventory.getSize();i++){
            if (inventory.getItem(i) != null){
                items.add(inventory.getItem(i));
            }
        }
        if (random.nextInt(5) == 0) items.add(WandHandler.getCraftingItem(1));

        inventory.clear();
    }

    @Override
    protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
        this.drops = items;
        super.dropDeathLoot(damagesource, i, flag);
    }

    @Override
    protected void initPathfinder() {
        this.bP.a(0,new PathfinderGoalAvoidTarget<EntityPlayer>(this,EntityPlayer.class,15,1.0d,1.0d));
        this.bP.a(1,new PathfinderGoalPanic(this,2.0d));
        this.bP.a(2,new PathfinderGoalRandomStrollLand(this,0.6d));
        this.bP.a(3,new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        return super.damageEntity(damagesource, f);
    }
}
