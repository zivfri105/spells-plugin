package com.axowattle.extraspells.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//❌✔

public class Spell {
    private final String name;
    private final String description;
    private final int levelRequired;
    private final int manaCost;

    public Spell(String name,String description,int levelRequired,int manaCost){
        this.name = name;
        this.description = description;
        this.levelRequired = levelRequired;
        this.manaCost = manaCost;
    }

    public final String getName() {
        return name;
    }

    public final int getLevelRequired() {
        return levelRequired;
    }

    public final String getDescription() {
        return description;
    }

    public final ItemStack getMenuItem(Player caster){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK,1);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setUnbreakable(true);
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "Level Required: " + levelRequired);
        itemLore.add("");
        itemLore.addAll(Arrays.asList(ChatColor.translateAlternateColorCodes('&',description).split("\n")));

        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public boolean onSpellCast(Player caster){
        return false;
    }

    public final int getManaCost() {
        return manaCost;
    }
}
