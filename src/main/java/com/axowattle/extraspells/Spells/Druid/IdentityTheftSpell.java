package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.Spell;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class IdentityTheftSpell extends Spell {


    public IdentityTheftSpell() {
        super("Identity Theft", "Change name and skin", 4, 500);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onSpellCast(Player caster) {
        super.onSpellCast(caster);

        Inventory inventory = Bukkit.createInventory(caster,54,"Chose Witch Player You Want.");

        for(Player player : Bukkit.getOnlinePlayers()){
            ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);

            SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
            meta.setDisplayName(ChatColor.GOLD + player.getName());
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(SpellHandler.playerNameKey, PersistentDataType.STRING,player.getName());

            itemStack.setItemMeta(meta);

            inventory.addItem(itemStack);
        }


        PlayerData.getPlayerData(caster).inventoryOpened = 4;

        caster.openInventory(inventory);
        return true;
    }
}
