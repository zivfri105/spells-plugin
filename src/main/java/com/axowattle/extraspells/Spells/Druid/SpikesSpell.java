package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpikesSpell extends Spell {
    private static Map<ArmorStand,Float> spikes = new HashMap();
    private static List<ArmorStand> removeFromMap = new ArrayList<>();

    public SpikesSpell() {
        super("Spikes", "Spawn spikes to all nearby entities.", 2, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        Location loc = caster.getLocation();
        Vector dir = loc.getDirection().multiply(2);
        loc.add(dir);
        for (int i = 0;i < 10;i++) {
                ArmorStand armorStand = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(0,-1,0),EntityType.ARMOR_STAND);
                armorStand.getEquipment().setHelmet(new ItemStack(Material.FERN));
                armorStand.setInvulnerable(true);
                armorStand.setInvisible(true);
                armorStand.setGravity(false);
                spikes.put(armorStand,(float) loc.getY() - 1);
                loc.add(dir);
        }
        return true;
    }

    public static void moveAllSpikes(){
        for(ArmorStand spike : spikes.keySet()){
            spike.teleport(spike.getLocation().clone().add(0,.1,0));
            if (spike.getLocation().getY() - spikes.get(spike) > 3){
                removeFromMap.add(spike);
                spike.remove();
                continue;
            }
            for (Entity entity : spike.getLocation().getWorld().getEntities()) {
                if (entity.getLocation().distance(spike.getLocation()) < 1.5 & entity.getType().isAlive()) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.damage(6);
                }
            }
        }

        for (ArmorStand spike : removeFromMap){
            spikes.remove(spike);
        }
        removeFromMap.clear();


    }
}
