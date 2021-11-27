package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Handlers.SphereHandler;
import com.axowattle.extraspells.Spells.Spell;
import com.axowattle.extraspells.Spheres.SpikesBallSphere;
import org.bukkit.entity.Player;

public class SpikesBallSpell extends Spell {
    public SpikesBallSpell() {
        super("Spikes Ball", "Creates a spikes ball.", 5, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        SphereHandler.addSphere(new SpikesBallSphere(caster));
        return true;
    }
}
