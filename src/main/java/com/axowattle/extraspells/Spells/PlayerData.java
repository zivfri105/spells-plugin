package com.axowattle.extraspells.Spells;

import com.axowattle.extraspells.ConfigManagers.ConfigFile;
import com.axowattle.extraspells.ExtraSpells;
import com.axowattle.extraspells.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public final class PlayerData {
    static List<PlayerData> playersData = new ArrayList<>();
    static ConfigFile playersDataConfig = new ConfigFile(ExtraSpells.getInstance(),"PlayersData");
    public final String player;
    public int level;
    public int point;
    public PlayerClass playerClass;
    public Integer[] selectedSpells;
    public int inventoryOpened;
    public List<Boolean> clicks = new ArrayList<>();
    public long timeClicked;
    public int mana;
    public boolean canFly;

    public PlayerData(Player player){
        this.player = player.getName();
        this.level = 0;
        this.point = 0;
        this.playerClass = null;
        this.inventoryOpened = 0;
        this.selectedSpells = new Integer[]{-1,-1,-1,-1,-1,-1,-1,0};
        this.mana = 0;
        this.canFly = false;

    }

    public PlayerData(ConfigurationSection configurationSection){
        this.player = configurationSection.getName();
        this.level = configurationSection.getInt("level");
        this.point = configurationSection.getInt("point");
        this.playerClass = SpellHandler.getPlayerClass(configurationSection.getString("class"));
        this.selectedSpells = configurationSection.getIntegerList("SelectedSpells").toArray(new Integer[8]);
        this.inventoryOpened = 0;
        this.mana = 0;
    }


    public static PlayerData getPlayerData(Player player)
    {
        for(PlayerData playerData : playersData){
            if (playerData.player.equals(player.getName())){
                return playerData;
            }
        }
        playersData.add(new PlayerData(player));
        getPlayerData(player);
        return null;
    }

    public void save(FileConfiguration config){
        if(!config.contains(player)){
            ConfigurationSection data = config.createSection(player);
            data.addDefault("level",level);
            data.addDefault("point",point);
            data.addDefault("class",playerClass.getName());
            data.addDefault("SelectedSpells",selectedSpells);
        }else {
            ConfigurationSection data = config.getConfigurationSection(player);
            data.set("level",level);
            data.set("point",point);
            if (playerClass != null)
                data.set("class",playerClass.getName());
            data.set("SelectedSpells",selectedSpells);
        }
    }

    public void openInventory(int inventoryOpened){
        this.inventoryOpened = inventoryOpened;
    }

    public void resetInventory(){
        openInventory(0);
    }

    public boolean isInInventory(int inventory){
        return inventoryOpened == inventory;
    }

    public int getSpellSlot(int spell){
        for (int i = 0;i< selectedSpells.length;i++)
        {
            if (selectedSpells[i] == spell){
                return i;
            }
        }
        return -1;
    }

    public int getMaxMana(){
        PlayerInventory inventory = Bukkit.getPlayer(player).getInventory();

        int itemAmount = 0;

        ItemStack[] items = new ItemStack[]{inventory.getItemInMainHand(),inventory.getItemInOffHand(),inventory.getHelmet(),inventory.getChestplate(),inventory.getLeggings(),inventory.getBoots()};
        for (ItemStack item : items){
            if (item != null){
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta != null) {
                    PersistentDataContainer data = itemMeta.getPersistentDataContainer();
                    if (data.has(SpellHandler.manaMaxKey, PersistentDataType.INTEGER))
                        itemAmount += data.get(SpellHandler.manaMaxKey, PersistentDataType.INTEGER);
                }
            }
        }

        return playerClass.getMaxMana() + itemAmount;
    }
}

