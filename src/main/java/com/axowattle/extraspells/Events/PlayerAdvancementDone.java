package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.ChatColor;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class PlayerAdvancementDone implements Listener {
    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        Advancement advancement = event.getAdvancement();
        String name = advancement.getKey().getKey();
        int advp = SpellHandler.getAdvancementAmount(name);
        PlayerData.getPlayerData(player).point += advp;


        if (advp > 0){
            player.sendMessage(ChatColor.GREEN + "You just got " + advp + " points by completing an advancement.");
        }
    }
}
