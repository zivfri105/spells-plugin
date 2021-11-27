package com.axowattle.extraspells.Spells.ArceaneArcher;

import com.axowattle.extraspells.ArrowEffects.ArrowHandler;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ArrowDamageSpell extends Spell {
    public ArrowDamageSpell() {
        super("Arrow Damage", "&7Adds Damage to arrows", 1, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        if (ArrowHandler.addEffectToPlayer(caster,ArrowHandler.getArrowEffect("ArrowDamage"))) {
            caster.sendMessage(ChatColor.DARK_PURPLE + "Your arrow damage has been increased by 4 to a total of " + 4 * ArrowHandler.getPlayerEffectsMap(caster)
                    .get(ArrowHandler.getArrowEffect("ArrowDamage").getId()));
            return true;
        }
        else
            return false;
    }
}
