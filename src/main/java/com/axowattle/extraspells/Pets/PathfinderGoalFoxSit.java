package com.axowattle.extraspells.Pets;

import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;

import java.util.EnumSet;

public class PathfinderGoalFoxSit extends PathfinderGoal {
    private final FoxPet entity;

    public PathfinderGoalFoxSit(FoxPet tamableFox) {
        this.entity = tamableFox;
        this.a(EnumSet.of(Type.a, Type.c));
    }

    public boolean b() {
        return this.entity.sitting;
    }

    public boolean a() {
        if (!this.entity.isOnGround()) {
            return false;
        }
        // EntityLiving entityliving = this.entity.owner;
        return entity.sitting;

    }

    public void c() {
        this.entity.getNavigation().o();
        this.entity.setSitting(true);
    }

    public void d() {
        this.entity.setSitting(false);
    }
}
