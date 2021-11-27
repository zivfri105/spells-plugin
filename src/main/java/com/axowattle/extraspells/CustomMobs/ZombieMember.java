package com.axowattle.extraspells.CustomMobs;

import com.axowattle.extraspells.CustomMobs.OriginalClasses.GangLeader;
import com.axowattle.extraspells.CustomMobs.OriginalClasses.GangMember;
import com.axowattle.extraspells.CustomMobs.OriginalClasses.PathfinderGoalFollowLeader;
import com.axowattle.extraspells.CustomMobs.OriginalClasses.PathfinderGoalZombieGangAttack;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityIronGolem;
import net.minecraft.world.entity.animal.EntityTurtle;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.entity.npc.EntityVillagerAbstract;
import net.minecraft.world.entity.player.EntityHuman;

import java.util.ArrayList;
import java.util.Random;

public class ZombieMember extends GangMember {

    private static Random random = new Random();
    private static EntityTypes[] entityTypes = new EntityTypes[]{EntityTypes.be,EntityTypes.N,EntityTypes.s};

    public ZombieMember(GangLeader leader) {
        super(leader,entityTypes[random.nextInt(entityTypes.length)],"Zombie Gang Member",50);
        this.setBaby(true);
    }

    @Override
    protected void initPathfinder() {
        this.bP.a(2, new PathfinderGoalZombieGangAttack(this, 1.0D, false));
        this.bP.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.bQ.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[]{EntityPigZombie.class}));
        this.bQ.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        if (this.t.spigotConfig.zombieAggressiveTowardsVillager) {
            this.bQ.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityVillagerAbstract.class, false));
        }

        this.bQ.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
        this.bQ.a(5, new PathfinderGoalNearestAttackableTarget(this, EntityTurtle.class, 10, true, false, EntityTurtle.bU));

        this.bP.a(5,new PathfinderGoalFollowLeader(this,1.0d,3));

        this.bP.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.bP.a(8, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
        this.drops = new ArrayList<>();
    }
}
