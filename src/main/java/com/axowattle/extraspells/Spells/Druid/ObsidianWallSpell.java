package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.ObsidianWallProjectile;
import com.axowattle.extraspells.Projectiles.TreeProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class ObsidianWallSpell extends Spell {
    public ObsidianWallSpell() {
        super("Obsidian Wall ", "Creates a wall of obsidian in front of you.", 2, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new ObsidianWallProjectile(caster));
        return true;
    }
}
