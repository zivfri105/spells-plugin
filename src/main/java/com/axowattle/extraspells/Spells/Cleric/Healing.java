package com.axowattle.extraspells.Spells.Cleric;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Healing extends Spell {
    public Healing(){
        super("Healing","&7Heals you 3 hearts.",1,100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        double health = caster.getHealth() + 6;
        if (health > caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())
            health = caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        caster.setHealth(health);
        return true;
    }
}
