package com.axowattle.extraspells.CustomMobs.OriginalClasses;

import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.monster.EntityMonster;
import net.minecraft.world.entity.monster.EntityZombie;

public class PathfinderGoalZombieGangAttack extends PathfinderGoalMeleeAttack {
    private final EntityMonster b;
    private int c;

    public PathfinderGoalZombieGangAttack(EntityMonster var0, double var1, boolean var3) {
        super(var0, var1, var3);
        this.b = var0;
    }

    public void c() {
        super.c();
        this.c = 0;
    }

    public void d() {
        super.d();
        this.b.setAggressive(false);
    }

    public void e() {
        super.e();
        ++this.c;
        if (this.c >= 5 && this.j() < this.k() / 2) {
            this.b.setAggressive(true);
        } else {
            this.b.setAggressive(false);
        }

    }
}
