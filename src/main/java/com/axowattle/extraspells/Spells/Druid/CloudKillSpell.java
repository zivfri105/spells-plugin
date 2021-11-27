package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Spells.ClonedInventory;
import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.Spell;
import com.axowattle.extraspells.TempBlocks.TempBlocksHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloudKillSpell extends Spell {
    private static Map<Player, ClonedInventory> cloudPlayers = new HashMap<>();

    public CloudKillSpell() {
        super("Cloud Kill", "Make you a cloud that gives you power.", 8, 500);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        super.onSpellCast(caster);

        PlayerData playerData = PlayerData.getPlayerData(caster);
        PlayerInventory inventory = caster.getInventory();

        if (cloudPlayers.containsKey(caster)) {
            resetInventory(caster);
        } else {
            cloudPlayers.put(caster, new ClonedInventory(inventory));
            playerData.canFly = true;
            caster.setFlying(true);
            inventory.clear();

            for(int i = 0;i< inventory.getSize();i++){
                inventory.setItem(i,new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
            }

            inventory.setHelmet(getItem(Material.IRON_HELMET));
            inventory.setChestplate(getItem(Material.IRON_CHESTPLATE));
            inventory.setLeggings(getItem(Material.IRON_LEGGINGS));
            inventory.setBoots(getItem(Material.IRON_BOOTS));
            inventory.setItemInOffHand(getItem(Material.SHIELD));
        }
        return true;
    }

    public static boolean isCloud(Player player){
        return cloudPlayers.containsKey(player);
    }

    public static Map<Player, ClonedInventory> getCloudPlayers() {
        return cloudPlayers;
    }

    public static void resetInventory(Player player){
        PlayerInventory inventory = player.getInventory();
        PlayerData playerData = PlayerData.getPlayerData(player);
        cloudPlayers.get(player).setInventory(inventory);
        cloudPlayers.remove(player);
        playerData.canFly = false;
        player.setFlying(false);
    }

    private static ItemStack getItem(Material material){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.DURABILITY,1,false);
        meta.addItemFlags(ItemFlag.HIDE_DYE,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
}
