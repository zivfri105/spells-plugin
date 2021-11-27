package com.axowattle.extraspells.Tasks;

import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ManaRegenerate implements Runnable{
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerData playerData = PlayerData.getPlayerData(player);
            if (playerData.playerClass != null) {
                if (playerData.mana < playerData.getMaxMana()) {
                    playerData.mana += 1;
                }
            }
        }
    }
}
