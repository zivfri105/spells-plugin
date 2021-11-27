package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.ExplosionProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class ExplosionSpell extends Spell {

    public ExplosionSpell() {
        super("Explosion Bullet", "Shoots a projectile that create an explosion.", 2, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new ExplosionProjectile(caster));
        return true;
    }
}
