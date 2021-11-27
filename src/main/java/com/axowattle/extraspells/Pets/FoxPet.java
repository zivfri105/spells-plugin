package com.axowattle.extraspells.Pets;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.EntityFox;
import net.minecraft.world.entity.animal.EntityWolf;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftFox;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;;

public class FoxPet extends EntityFox {
    public EntityLiving owner;
    public boolean sitting;
    public FoxPet(Location loc, Player player) {
        super(EntityTypes.E,((CraftWorld) loc.getWorld()).getHandle());
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        this.setBaby(false);
        this.setInvulnerable(false);

        this.owner = ((CraftPlayer)player).getHandle();

        this.setGoalTarget(((CraftPlayer)player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM,true);
    }


    @Override
    protected void initPathfinder() {
        this.bP.a(0,new PathfinderGoalFloat(this));
        this.bP.a(1,new PathfinderGoalFoxSit(this));
        this.bP.a(2,new PathfinderGoalPet(this,1,15,2));
        this.bP.a(3,new PathfinderGoalFoxSleep(this));
        this.bP.a(4,new PathfinderGoalLookAtPlayer(this, EntityHuman.class,8f));
        this.bP.a(5, new PathfinderGoalRandomLookaround(this));

    }

    public void toggleSitting(){
        sitting = !sitting;
    }
}
