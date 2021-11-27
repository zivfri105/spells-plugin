package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class FireballSpell extends Spell {
    public FireballSpell() {
        super("Fireball", "Shoots a fireball.", 6, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {

        Fireball fireball = (Fireball) caster.getWorld().spawnEntity(caster.getLocation(), EntityType.FIREBALL);
        fireball.setDirection(caster.getLocation().getDirection());
        fireball.setShooter(caster);
        fireball.setYield(20);
        fireball.setBounce(true);
        fireball.setCustomNameVisible(false);
        fireball.setCustomName("gfgvhjGUYfguiT58TiuU()*T687$&^GuiHB");

        return true;
    }
}
