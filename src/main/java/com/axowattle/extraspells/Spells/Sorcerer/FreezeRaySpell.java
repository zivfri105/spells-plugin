package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.FreezeProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class FreezeRaySpell extends Spell {
    public FreezeRaySpell() {
        super("Freeze Ray", "Freezes your enemy.", 4, 75);
    }


    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new FreezeProjectile(caster));
        return true;
    }
}
