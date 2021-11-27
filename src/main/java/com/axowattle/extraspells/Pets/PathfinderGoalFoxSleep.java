package com.axowattle.extraspells.Pets;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.animal.EntityFox;
import org.bukkit.Bukkit;

import java.util.EnumSet;

public class PathfinderGoalFoxSleep extends PathfinderGoal {
    private final FoxPet a; // our pet
    private EntityLiving b; // owner <-- player

    public PathfinderGoalFoxSleep(FoxPet a) {
        this.a = a;
        this.a(EnumSet.of(Type.a,Type.b,Type.c));
    }


    @Override
    public boolean a() {
        this.b = this.a.getGoalTarget();
        if (this.b == null) return false;
        if (this.a.isSleeping()) return false;
        return this.b.isSleeping();
    }

    @Override
    public void c() {
        this.a.setSitting(false);
        this.a.setCrouching(false);
        this.a.y(false);
        this.a.setJumping(false);
        this.a.setSleeping(true);
        this.a.getNavigation().o();
        //this.a.getControllerMove().a(this.a.locX(), this.a.locY(), this.a.locZ(), 0.0D);
    }

    @Override
    public void d() {
        this.a.setSleeping(false);
    }
}
