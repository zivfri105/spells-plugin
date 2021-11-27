package com.axowattle.extraspells.CustomMobs.OriginalClasses;

import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EnumItemSlot;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomMobsHandler {
    public static void setEquipmentLeader(EntityInsentient entity){
        entity.setSlot(EnumItemSlot.a,randomEnchantment(new ItemStack(Material.NETHERITE_SWORD)));
        entity.setSlot(EnumItemSlot.c,randomEnchantment(new ItemStack(Material.NETHERITE_BOOTS)));
        entity.setSlot(EnumItemSlot.d,randomEnchantment(new ItemStack(Material.NETHERITE_LEGGINGS)));
        entity.setSlot(EnumItemSlot.e,randomEnchantment(new ItemStack(Material.NETHERITE_CHESTPLATE)));
        entity.setSlot(EnumItemSlot.f,randomEnchantment(new ItemStack(Material.NETHERITE_HELMET)));

    }
    public static void setEquipmentMember(EntityInsentient entity){
        entity.setSlot(EnumItemSlot.a,randomEnchantment(new ItemStack(Material.DIAMOND_SWORD)));
        entity.setSlot(EnumItemSlot.c,randomEnchantment(new ItemStack(Material.DIAMOND_BOOTS)));
        entity.setSlot(EnumItemSlot.d,randomEnchantment(new ItemStack(Material.DIAMOND_LEGGINGS)));
        entity.setSlot(EnumItemSlot.e,randomEnchantment(new ItemStack(Material.DIAMOND_CHESTPLATE)));
        entity.setSlot(EnumItemSlot.f,randomEnchantment(new ItemStack(Material.DIAMOND_HELMET)));

    }
    public static net.minecraft.world.item.ItemStack randomEnchantment(ItemStack item) {
        // Store all possible enchantments for the item
        List<Enchantment> possible = new ArrayList<Enchantment>();

        // Loop through all enchantemnts
        for (Enchantment ench : Enchantment.values()) {
            // Check if the enchantment can be applied to the item, save it if it can
            if (ench.canEnchantItem(item)) {
                possible.add(ench);
            }
        }

        // If we have at least one possible enchantment
        if (possible.size() >= 1) {
            // Randomize the enchantments
            Collections.shuffle(possible);
            // Get the first enchantment in the shuffled list
            Enchantment chosen = possible.get(0);
            // Apply the enchantment with a random level between 1 and the max level
            item.addEnchantment(chosen, 1 + (int) (Math.random() * ((chosen.getMaxLevel() - 1) + 1)));
        }

        // Return the item even if it doesn't have any enchantments
        return CraftItemStack.asNMSCopy(item);
    }
}
