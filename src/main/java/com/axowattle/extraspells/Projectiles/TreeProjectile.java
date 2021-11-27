package com.axowattle.extraspells.Projectiles;

import com.axowattle.extraspells.Projectile.SpellProjectile;
import com.axowattle.extraspells.Spells.SpellHandler;
import com.axowattle.extraspells.TempBlocks.TempBlocks;
import com.axowattle.extraspells.TempBlocks.TempBlocksHandler;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TreeProjectile extends SpellProjectile {
    public TreeProjectile(Player source) {
        super(source, 1, 0, 100, Particle.TOTEM);
    }

    @Override
    public void onProjectileHit() {
        super.onProjectileHit();

        TempBlocksHandler.addBlocks(getLocation(),SpellHandler.getTreeData(),1000);
    }
}
