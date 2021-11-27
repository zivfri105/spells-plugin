package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.Spell;
import com.axowattle.extraspells.Tasks.SpellCaster;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayerInteract implements Listener {
    private static Map<String,Long> playersTime = new HashMap<>();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player);
        if (playerData.playerClass == null) return;
        if (!playersTime.containsKey(player.getName())) playersTime.put(player.getName(),0l);
        if (SpellHandler.isWand(player.getInventory().getItemInMainHand()) & playersTime.get(player.getName()) != SpellCaster.timeFromStart){
            if (event.getAction() == Action.LEFT_CLICK_AIR | event.getAction() == Action.LEFT_CLICK_BLOCK ){
                playerData.clicks.add(false);
            }else if (event.getAction() == Action.RIGHT_CLICK_AIR | event.getAction() == Action.RIGHT_CLICK_BLOCK ){
                playerData.clicks.add(true);
            }

            String text = "";

            for(int i = 0;i< playerData.clicks.size();i++){
                if (playerData.clicks.get(i)){
                    text += ChatColor.GREEN + "R";
                }else {
                    text += ChatColor.GREEN + "L";
                }
                if (i != playerData.clicks.size() - 1){
                    text += ChatColor.GRAY + "-";
                }
            }

            player.sendTitle(text," ", 1, 20, 5);

            if (playerData.clicks.size() > 2){
                int slot = 0;
                if (playerData.clicks.get(0))
                    slot += 1;
                if (playerData.clicks.get(1))
                    slot += 2;
                if (playerData.clicks.get(2))
                    slot += 4;
                int spellSlot = playerData.selectedSpells[slot];
                if (spellSlot == -1){
                    player.sendMessage(ChatColor.RED + "This spell slot is empty.");
                }else {
                    Spell spell = SpellHandler.getAvailableSpells(player).get(spellSlot);
                    if (spell.getManaCost() > playerData.mana)
                        player.sendMessage(ChatColor.RED + "You don't have enough mana to do this spell.");
                    else{
                        if (spell.onSpellCast(player))
                            playerData.mana -= spell.getManaCost();
                    }

                }
                playerData.clicks.clear();
            }
            playerData.timeClicked = SpellCaster.timeFromStart;



        }
        playersTime.put(player.getName(),SpellCaster.timeFromStart);

    }
}
