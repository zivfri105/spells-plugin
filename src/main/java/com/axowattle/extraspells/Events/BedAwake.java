package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.ExtraSpells;
import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BedAwake implements Listener {
    @EventHandler
    public void onPlayerWake(PlayerBedLeaveEvent event) {
        int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ExtraSpells.getInstance(), new Runnable() {
            public void run() {
                if (isDay()) {
                    SpellHandler.openSpellsSelectMenu(event.getPlayer());
                    PlayerData playerData = PlayerData.getPlayerData(event.getPlayer());
                    playerData.mana = playerData.playerClass.getMaxMana();
                }
            }

        }, 1L);
    }

    public boolean isDay() {
        Server server = Bukkit.getServer();
        long time = server.getWorld("world").getTime();

        return time < 12300 || time > 23850;
    }
}
