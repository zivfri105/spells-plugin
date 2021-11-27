package com.axowattle.extraspells.Armors;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemAbility {
    protected String name;
    protected String description;
    protected ActionType actionType;

    public ItemAbility(String name,String description,ActionType actionType){
        this.name = name;
        this.description = description;
        this.actionType = actionType;
    }

    public abstract void onAbilityActivate(ActionEvent event);

    public final List<String> getDescriptionAdd(String prefix){
        List<String> returnDescription = new ArrayList<>();
        returnDescription.addAll(List.of(ChatColor.translateAlternateColorCodes('&',prefix + description).split("\n")));
        return returnDescription;
    }

    public enum ActionType{
        RIGHT_CLICK("right_click","RIGHT CLICK"),
        LEFT_CLICK("left_click","LEFT CLICK"),
        PLAYER_SHIFT("player_shift","SNEAK"),
        PLAYER_SPRINT("player_sprint","SPRINT"),
        PLAYER_DAMAGE("player_damage","PLAYER DAMAGE"),
        DAMAGE_PLAYER("damage_player","DAMAGE");

        public String id;
        public String display;
        ActionType(String id,String display){
            this.id = id;
            this.display = display;
        }
    }

    public static class ActionEvent{
        public Player player;
        public Entity targetEntity;
        public Block targetBlock;
        public double amount;
        public boolean isCanceled;
    }
}
