package com.axowattle.extraspells.CustomMobs.OriginalClasses;

import com.axowattle.extraspells.CustomMobs.ZombieLeader;
import com.axowattle.extraspells.CustomMobs.ZombieMember;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;

import java.util.EnumSet;

public class PathfinderGoalFollowLeader extends PathfinderGoal {

    private final GangMember a; // wolf member
    private GangLeader b; // leader

    private final double f; // following speed
    private final float md; // following distance

    private double c; // x
    private double d; // y
    private double e; // z


    public PathfinderGoalFollowLeader(GangMember a, double speed,float minDistance) {
        this.a = a;
        this.f = speed;
        this.md = minDistance;
        this.a(EnumSet.of(Type.a));
    }

    @Override
    public boolean a() {
        // Will start event if this is true
        // runs every tick
        if (this.b == null){
            this.b = this.a.getLeader();
        }

        if (this.b.f(this.a) < (double) (md * md) | !this.a.isOnGround()) {
            return false;
        }
        else {

            this.c = this.b.locX(); // x
            this.d = this.b.locY(); // y
            this.e = this.b.locZ(); // z
            return true;
            // call upon c()
        }
    }

    public void c() {
        // runner :)                x      y        z    speed
        this.a.getNavigation().a(this.c, this.d, this.e, this.f);
    }

    public boolean b() {
        // run every tick as long as its true (repeats c)
        return !this.a.getNavigation().m();
    }

    public void d() {
        // runs when done (b is false)
        this.b = null;
    }
}
