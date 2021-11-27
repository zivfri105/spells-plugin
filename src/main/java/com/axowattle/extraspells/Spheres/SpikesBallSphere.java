package com.axowattle.extraspells.Spheres;

import com.axowattle.extraspells.Handlers.Sphere;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpikesBallSphere extends Sphere {
    private Player player;

    public SpikesBallSphere(Player player) {
        super(player.getLocation(), new ItemStack(Material.FERN), 3, 100, 1);
        this.player = player;
    }

    @Override
    public void onSphereMove() {
        super.onSphereMove();

        for (LivingEntity entity : getAllEntities()){
            if (entity.getUniqueId() == player.getUniqueId()) continue;
            entity.damage(40,player);
        }
    }
}
