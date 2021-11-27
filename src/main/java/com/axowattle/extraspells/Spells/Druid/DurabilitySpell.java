package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class DurabilitySpell extends Spell {
    public DurabilitySpell() {
        super("Durability", "Adds a quarter of the durability of the item\nyou are holding in your off hand.", 3, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        PlayerInventory inventory = caster.getInventory();
        short durability = inventory.getItemInOffHand().getDurability();
        short maxDurability = inventory.getItemInOffHand().getType().getMaxDurability();
        if (durability - maxDurability / 4 > maxDurability) durability = 0;
        else durability -= maxDurability / 4;

        inventory.getItemInOffHand().setDurability(durability);
        return true;
    }
}
