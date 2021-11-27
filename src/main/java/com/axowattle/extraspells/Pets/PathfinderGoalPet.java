package com.axowattle.extraspells.Pets;

import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;

import java.util.EnumSet;

public class PathfinderGoalPet extends PathfinderGoal {

    private final FoxPet a; // our pet
    private EntityLiving b; // owner <-- player

    private final double f; // pets speed
    private final float g; // max distance between owner and pet
    private final float G; // distance between owner and pet

    private double c; // x
    private double d; // y
    private double e; // z

    public PathfinderGoalPet(FoxPet a, double speed,float distance,float minDistance) {
        this.a = a;
        this.f = speed;
        this.g = distance;
        this.G = minDistance;
        this.a(EnumSet.of(Type.a));
    }


    @Override
    public boolean a() {
        // will start the pathfinder goal only if it returns true
        // runs every tick

        this.b = this.a.getGoalTarget();
        if (this.b == null) return false;
        if (this.b.f(this.a) > (double) (this.g * this.g) & this.b.isOnGround()){
            // teleport pet to owner
            a.setPosition(this.b.locX(),this.b.locY(),this.b.locZ());
            return false;
        }
        if (this.b.f(this.a) < (double) (this.G * this.G)) return false;
        if (!this.a.isOnGround()) return false;


        this.c = this.b.locX(); // x
        this.d = this.b.locY(); // y
        this.e = this.b.locZ(); // z

        return true;
    }

    @Override
    public void c() {
        // runner                 x      y       z    speed
        this.a.getNavigation().a(this.c,this.d,this.e,this.f);
    }

    @Override
    public boolean b() {
        // runs after c()
        // runs every tick as long as its true
        return !this.a.getNavigation().m() && this.b.f(this.a) < (double) (this.g * this.g);
    }

    @Override
    public void d() {
        // rus when b returns false
        this.b = null;
    }
}
