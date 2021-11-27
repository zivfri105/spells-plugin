package com.axowattle.extraspells.Spells.ArceaneArcher;

import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.FireBoltProjectile;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Collection;

public class ArrowGunSpell extends Spell {



    public ArrowGunSpell() {
        super("Arrow Gun", "Shots an arrow", 8, 20 );
    }

    @Override
    public boolean onSpellCast(Player caster) {
        caster.launchProjectile(Arrow.class, caster.getLocation().getDirection());
        return true;
    }

}