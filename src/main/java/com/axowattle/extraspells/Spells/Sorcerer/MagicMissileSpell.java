package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.MagicMissileProjectile;
import com.axowattle.extraspells.Projectiles.TreeProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class MagicMissileSpell extends Spell {
    public MagicMissileSpell() {
        super("Magic Missile", "Shoots a bullet that deals damage", 1, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new MagicMissileProjectile(caster));
        return true;
    }
}
