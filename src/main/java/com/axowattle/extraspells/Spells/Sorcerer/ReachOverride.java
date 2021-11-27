package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ReachOverride extends Spell {

    public ReachOverride() {
        super("Reach Override", "Can help you place blocks from a far.", 5, 40);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        Location loc = caster.getTargetBlock(null,100).getLocation();
        loc.add(caster.getLocation().toVector().subtract(loc.toVector()).normalize());
        Block block = loc.getBlock();
        if (block.getType().isSolid())
            return false;
        if (!caster.getInventory().getItemInOffHand().getType().isBlock())
            return false;
        block.setType(caster.getInventory().getItemInOffHand().getType());
        return true;
    }
}
