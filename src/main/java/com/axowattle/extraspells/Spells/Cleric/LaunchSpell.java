package com.axowattle.extraspells.Spells.Cleric;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.LaunchProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LaunchSpell extends Spell {
    public LaunchSpell() {
        super("Launch", "Creates a bolt that launches the hit target to the air.", 4, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new LaunchProjectile(caster));
        return true;
    }
}
