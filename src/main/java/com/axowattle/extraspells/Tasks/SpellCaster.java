package com.axowattle.extraspells.Tasks;

import com.axowattle.extraspells.ArrowEffects.ArrowHandler;
import com.axowattle.extraspells.Handlers.SphereHandler;
import com.axowattle.extraspells.Npcs.NPCHandler;
import com.axowattle.extraspells.PassiveAbilities.PassiveAbility;
import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Spells.Druid.CloudKillSpell;
import com.axowattle.extraspells.Spells.Druid.SpikesSpell;
import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.Sorcerer.InvisibilitySpell;
import com.axowattle.extraspells.Spells.SpellHandler;
import com.axowattle.extraspells.TempBlocks.TempBlocksHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpellCaster implements Runnable{

    public static long timeFromStart = 0;

    @Override
    public void run() {
        ProjectileHandler.moveAll();
        ProjectileHandler.activateRemoval();
        ArrowHandler.moveAllArrows();
        TempBlocksHandler.resetBlocks();
        SpikesSpell.moveAllSpikes();
        InvisibilitySpell.clearInvisiblePlayersArmor();
        SphereHandler.tick();
        NPCHandler.onTick();
        for (Player player : Bukkit.getOnlinePlayers()){
            PlayerData playerData = PlayerData.getPlayerData(player);
            if (playerData.playerClass != null) {
                if (timeFromStart - playerData.timeClicked == 20) {
                    playerData.clicks.clear();
                }
                player.spigot().sendMessage(
                        ChatMessageType.ACTION_BAR,
                        new TextComponent(ChatColor.AQUA + "Mana: " + playerData.mana));
                for (PassiveAbility passiveAbility : PlayerData.getPlayerData(player).playerClass.getAvailablePassiveAbilities(player)) {
                    passiveAbility.onTick(player);
                }
            }


        }
        timeFromStart++;
    }

    public static String get(Player player) {
        float yaw = player.getLocation().getYaw();
        if (yaw < 0) {
            yaw += 360;
        }
        if (yaw >= 315 || yaw < 45) {
            return "S";
        } else if (yaw < 135) {
            return "W";
        } else if (yaw < 225) {
            return "N";
        } else if (yaw < 315) {
            return "E";
        }
        return "N";
    }
}
