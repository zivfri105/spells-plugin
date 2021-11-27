package com.axowattle.extraspells.Citizens.Npcs;

import net.citizensnpcs.api.ai.tree.IfElse;
import net.citizensnpcs.api.ai.tree.Sequence;
import net.citizensnpcs.api.ai.tree.TimerDecorator;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.persistence.Persist;
import net.citizensnpcs.api.trait.Trait;

public class Ghost extends Trait {

    //Persist("mysettingname") boolean automaticallyPersistedSetting = false;
    protected Ghost() {
        super("Ghost");
    }

    public void setupTree(NPC npc) {
        //npc.getGoalController().addGoal(Sequence.createSequence(new MyBehavior(), new MyAccumulateBehavior(), new MyParallelBehavior()));

        // A more complicated example
        /*npc.getGoalController().addGoal(Sequence.createSequence(
                new IfElse(() -> npc.isCool(),
                        TimerDecorator.tickLimiter(new MyLongRunningBehavior(), 100),
                        new MyElseBehavior()),
                new MyParallelBehavior()
        ));*/

        // You can implement nested loops and other behavior sequences using the API provided in net.citizensnpcs.api.ai.tree
    }


}
