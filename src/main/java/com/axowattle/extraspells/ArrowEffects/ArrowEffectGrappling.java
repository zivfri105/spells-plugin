package com.axowattle.extraspells.ArrowEffects;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

public class ArrowEffectGrappling extends ArrowEffect{
    private static final double grapplingStrength = 1;

    public ArrowEffectGrappling() {
        super("grappling",3);
    }

    @Override
    public void onArrowHitGround(Projectile arrow, Block block) {
        Player player = (Player) arrow.getShooter();
        Vector velocity = arrow.getLocation().toVector().subtract(player.getLocation().toVector()).multiply(grapplingStrength);
        player.setVelocity(velocity);
    }
}
