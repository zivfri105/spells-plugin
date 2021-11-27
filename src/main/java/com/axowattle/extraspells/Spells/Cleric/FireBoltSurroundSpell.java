package com.axowattle.extraspells.Spells.Cleric;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.FireBoltProjectile;
import com.axowattle.extraspells.Projectiles.FireBoltSurroundProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;

public class FireBoltSurroundSpell extends Spell {

    private int bulletAmount = 6;

    public FireBoltSurroundSpell() {
        super("Fire Circle", "&7Shots fire in every direction.", 2, 86);
    }


    @Override
    public boolean onSpellCast(Player caster) {
        for (int i = 0;i< bulletAmount;i++){
            ProjectileHandler.createProjectile(new FireBoltSurroundProjectile(caster,i * (360 / bulletAmount)));
        }
        return true;
    }
}
