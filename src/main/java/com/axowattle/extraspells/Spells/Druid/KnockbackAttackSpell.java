package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KnockbackAttackSpell extends Spell {
    private static List<Player> knockbackPlayers = new ArrayList<>();

    public KnockbackAttackSpell() {
        super("Knockback Attack", "Gives you extra knockback in your next attack.", 5, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        knockbackPlayers.add(caster);
        caster.sendMessage(ChatColor.DARK_PURPLE + "The next attack will have extra knockback.");
        return true;
    }

    public static boolean isKnockback(Player player){
        if (knockbackPlayers.contains(player)){
            knockbackPlayers.remove(player);
            return true;
        }
        return false;
    }
}
