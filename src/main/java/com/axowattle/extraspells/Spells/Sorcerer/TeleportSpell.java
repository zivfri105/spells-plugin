package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.TeleportProjectile;
import com.axowattle.extraspells.Projectiles.TreeProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class TeleportSpell extends Spell {
    public TeleportSpell() {
        super("Teleport", "Teleports You forward.", 1, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new TeleportProjectile(caster));
        return true;
    }
}
