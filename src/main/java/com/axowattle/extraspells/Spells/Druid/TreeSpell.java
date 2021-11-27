package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.TreeProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class TreeSpell extends Spell {
    public TreeSpell() {
        super("Tree Plant", "Plants a tree.", 1, 5);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new TreeProjectile(caster));
        return true;
    }
}
