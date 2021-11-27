package com.axowattle.extraspells.CustomMobs.OriginalClasses;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.monster.EntityMonster;
import net.minecraft.world.level.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public abstract class GangLeader extends EntityMonster {
    private static Random random = new Random();

    private int chance;
    private ItemStack item;
    protected Class<? extends GangMember> memberType;

    protected GangLeader(EntityTypes<? extends EntityMonster> type, Class<? extends GangMember> memberType, Location loc, String name, float health, ItemStack droppedItem,int chance) {
        super(type, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(),loc.getY(),loc.getZ());

        this.setCustomName(new ChatComponentText(ChatColor.translateAlternateColorCodes('&',name)));
        CustomMobsHandler.setEquipmentLeader(this);
        this.getAttributeInstance(GenericAttributes.a).setValue(health);
        this.setHealth(health);
        this.memberType = memberType;

        this.item = droppedItem;
        this.chance = chance;
    }

    public void spawnMembers(int groupMembersMin,int groupMembersMax) {
        try {
            World world = this.getWorld();
            for (int i = 0; i < groupMembersMin + (random.nextInt(groupMembersMax - groupMembersMin)); i++) {
                GangMember member = memberType.getDeclaredConstructor(GangLeader.class).newInstance(this);
                CustomMobsHandler.setEquipmentMember(member);
                world.addEntity(member);
            }
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            System.out.println(e);
        }

    }

    @Override
    protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
        this.drops = new ArrayList<>();

        if (random.nextDouble() * 100 < chance) this.drops.add(item);
    }
}
