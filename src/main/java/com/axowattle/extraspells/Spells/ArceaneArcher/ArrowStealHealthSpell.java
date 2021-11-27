package com.axowattle.extraspells.Spells.ArceaneArcher;

import com.axowattle.extraspells.ArrowEffects.ArrowHandler;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ArrowStealHealthSpell extends Spell {
    public ArrowStealHealthSpell() {
        super("Health Steal", "&7Gives you half of the health you deal.", 2, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        if (ArrowHandler.addEffectToPlayer(caster,ArrowHandler.getArrowEffect("ArrowHealthSteal"))) {
            caster.sendMessage(ChatColor.DARK_PURPLE + "The next arrow you shoot will give you health ");
            return true;
        }else {
            return false;
        }

    }
}
