package com.axowattle.extraspells.PassiveAbilities;

import com.axowattle.extraspells.ExtraSpells;
import com.axowattle.extraspells.Spells.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;
import java.util.Map;

public class DoubleJump extends PassiveAbility{
    private Map<Player,Boolean> doubleJump;

    public DoubleJump() {
        super("Double Jump", "Lets you double jump while pressing space in the air.", 3);
        doubleJump = new HashMap<>();
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().setAllowFlight(true);
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        doubleJump.remove(event.getPlayer());
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if ((player.isOnGround() || event.getTo().getBlock().isLiquid()) && !doubleJump.get(player)) {
            player.setAllowFlight(false);
            Bukkit.getScheduler().scheduleSyncDelayedTask(ExtraSpells.getInstance(), new Runnable() {
                @Override
                public void run() {
                    player.setAllowFlight(true);
                    doubleJump.put(player,true);
                }
            });
        }
        doubleJump.put(player,player.isOnGround());
    }

    @Override
    public void onPlayerFlyToggle(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        GameMode gameMode = player.getGameMode();

        if(gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR || player.isFlying() || PlayerData.getPlayerData(player).canFly) {
            return;
        }

        event.setCancelled(true);

        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(.5).setY(.5));
    }
}
