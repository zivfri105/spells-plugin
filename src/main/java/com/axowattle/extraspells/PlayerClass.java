package com.axowattle.extraspells;

import com.axowattle.extraspells.PassiveAbilities.PassiveAbility;
import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerClass {

    private String name;
    private int maxMana;
    private List<Spell> spells;
    private List<PassiveAbility> passiveAbilities;
    private ItemStack item;



    public PlayerClass(String name,int maxMana,ItemStack item, List<Spell> spells,List<PassiveAbility> passiveAbilities){
        this.spells = spells;
        this.name = name;
        this.item = item;
        this.maxMana = maxMana;
        this.passiveAbilities = passiveAbilities;
    }

    public final String getName() {
        return name;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public List<PassiveAbility> getAvailablePassiveAbilities(Player player){
        List<PassiveAbility> availablePassiveAbilities = new ArrayList<>();
        for (PassiveAbility passiveAbility : passiveAbilities){
            if (passiveAbility.getMinLevel() <= PlayerData.getPlayerData(player).level) availablePassiveAbilities.add(passiveAbility);
        }
        return availablePassiveAbilities;
    }
}
