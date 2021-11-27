package com.axowattle.extraspells.Citizens.Goals;

import net.citizensnpcs.api.ai.Goal;
import net.citizensnpcs.api.ai.GoalSelector;
import net.citizensnpcs.api.ai.tree.BehaviorGoalAdapter;
import net.citizensnpcs.api.ai.tree.BehaviorStatus;
import net.minecraft.world.entity.ai.behavior.BehaviorAttackTargetSet;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FollowOwnerBehavior implements Goal {
    private static final double maxDistance = 10;

    private Player owner;
    private NPC npc;

    @Override
    public void reset() {

    }

    @Override
    public void run(GoalSelector selector) {

    }

    @Override
    public boolean shouldExecute(GoalSelector selector) {
        return false;
    }
}
