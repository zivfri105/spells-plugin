package com.axowattle.extraspells.Spells.Cleric;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.FireBoltProjectile;
import com.axowattle.extraspells.Projectiles.LightningProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class LightningSpell extends Spell {
    public LightningSpell() {
        super("Lightning", "Shoot a lightning projectile.", 3, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new LightningProjectile(caster));
        return true;
    }
}
