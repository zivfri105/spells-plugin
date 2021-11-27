package com.axowattle.extraspells.Spells.Cleric;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.FireBoltProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class FireBoltSpell extends Spell {



    public FireBoltSpell() {
        super("Fire Bolt", "Shots a bolt of fire", 1, 20 );
    }

    @Override
    public boolean onSpellCast(Player caster) {
        ProjectileHandler.createProjectile(new FireBoltProjectile(caster));
        return true;
    }

}
