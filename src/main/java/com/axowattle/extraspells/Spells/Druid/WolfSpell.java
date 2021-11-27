package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Spells.Spell;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class WolfSpell extends Spell {
    public WolfSpell() {
        super("Wild Woolf", "Spawn a woolf that will help you.", 3, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        Wolf wolf = (Wolf) caster.getWorld().spawnEntity(caster.getLocation(), EntityType.WOLF);
        wolf.setOwner(caster);
        String name = ChatColor.DARK_BLUE + SpellHandler.getRandomName();
        wolf.setCustomName(name);
        wolf.setCustomNameVisible(true);

        caster.sendMessage( name + ChatColor.GREEN + " has spawned on your side.");
        return true;
    }
}
