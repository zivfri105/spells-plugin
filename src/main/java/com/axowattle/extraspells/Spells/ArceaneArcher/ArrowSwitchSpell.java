package com.axowattle.extraspells.Spells.ArceaneArcher;

import com.axowattle.extraspells.ArrowEffects.ArrowHandler;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ArrowSwitchSpell extends Spell {
    public ArrowSwitchSpell() {
        super("Arrow Switch", "&7Switch uoy with the hit position.", 3, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        if (ArrowHandler.addEffectToPlayer(caster,ArrowHandler.getArrowEffect("SwitchEffect"))) {
            caster.sendMessage(ChatColor.DARK_PURPLE + "Your arrow wil now have the switch effect.");
            return true;
        }else {
            return false;
        }

    }
}
