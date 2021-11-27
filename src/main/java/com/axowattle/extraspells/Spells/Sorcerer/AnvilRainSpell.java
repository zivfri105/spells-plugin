package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.AnvilRainProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class AnvilRainSpell extends Spell {
    public AnvilRainSpell() {
        super("Anvil Rain", "Summons an anvil above the enemy.", 4, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new AnvilRainProjectile(caster));
        return true;
    }
}
