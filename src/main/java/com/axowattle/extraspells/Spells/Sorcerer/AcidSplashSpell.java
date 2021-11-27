package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class AcidSplashSpell extends Spell {
    private static final double throwStrength = 3;

    public AcidSplashSpell() {
        super("Acid Splash", "Creates a splash potion of poison.", 4, 20);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        Vector dir = caster.getLocation().getDirection().clone();

        ItemStack itemStack = new ItemStack(Material.LINGERING_POTION);
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 10, 1), true);
        itemStack.setItemMeta(potionMeta);

        ThrownPotion potion = (ThrownPotion) caster.getWorld().spawnEntity(caster.getLocation().add(dir), EntityType.SPLASH_POTION);
        potion.setItem(itemStack);
        potion.setVelocity(dir.multiply(throwStrength));
        return true;
    }
}
