package com.axowattle.extraspells.Spells.ArceaneArcher;

import com.axowattle.extraspells.ArrowEffects.ArrowHandler;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ArrowProtectionSpell extends Spell {
    public ArrowProtectionSpell() {
        super("Protective Arrow", "&7Makes arrows surround you.", 1, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        if (ArrowHandler.addEffectToPlayer(caster,ArrowHandler.getArrowEffect("ProtectionArrow"))) {
            caster.sendMessage(ChatColor.DARK_PURPLE + "You arrow will now surround you.");
            return true;
        }else {
            return false;
        }

    }
}
