package com.axowattle.extraspells.CustomMobs.OriginalClasses;

import com.axowattle.extraspells.CustomMobs.ZombieLeader;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.monster.EntityMonster;
import net.minecraft.world.level.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import java.util.ArrayList;

public abstract class GangMember extends EntityMonster {

    protected GangLeader leader;

    protected GangMember( GangLeader leader,EntityTypes<? extends EntityMonster> entityType, String name,float health) {
        super(entityType, leader.getWorld());

        this.setPosition(leader.locX(),leader.locY(),leader.locZ());
        this.setCustomName(new ChatComponentText(ChatColor.translateAlternateColorCodes('&',name)));
        this.setCustomNameVisible(true);
        this.leader = leader;

        this.getAttributeInstance(GenericAttributes.a).setValue(health);
        this.setHealth(health);
    }

    @Override
    protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
        this.drops = new ArrayList<>();
    }

    public GangLeader getLeader() {
        return leader;
    }

}
