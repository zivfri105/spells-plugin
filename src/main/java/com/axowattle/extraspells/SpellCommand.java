package com.axowattle.extraspells;

import com.axowattle.extraspells.CustomMobs.ZombieLeader;
import com.axowattle.extraspells.Npcs.Ghost;
import com.axowattle.extraspells.Npcs.NPC;
import com.axowattle.extraspells.Npcs.NPCHandler;
import com.axowattle.extraspells.Pathfinding.PathfinderGrid;
import com.axowattle.extraspells.Pets.FoxPet;
import com.axowattle.extraspells.Pets.PetsHandler;
import com.axowattle.extraspells.Quests.QuestsHandler;
import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.SpellHandler;
import com.axowattle.extraspells.Spells.WandHandler;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Iterator;

public class SpellCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player & sender.hasPermission("extarsspells.admin")){
            Player player = (Player) sender;
            PlayerData playerData = PlayerData.getPlayerData(player);
            if (args[0].equalsIgnoreCase("wand")) {
                Inventory inventory = player.getInventory();
                inventory.addItem(WandHandler.getWandDefault());
                inventory.addItem(WandHandler.getWandNormal());
                inventory.addItem(WandHandler.getCraftingItem(1));
                inventory.addItem(WandHandler.getSuperFlesh(1));
                inventory.addItem(WandHandler.getManaArtifact(1));

            }
            else if(args[0].equalsIgnoreCase("class")){
                if (args.length > 1) {
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p == null) {
                        player.sendMessage(ChatColor.RED + "There is no player named " + args[1]);
                        return true;
                    }
                    SpellHandler.openClassSelectMenu(p);
                }else {
                    SpellHandler.openClassSelectMenu(player);
                }
            }else if(args[0].equalsIgnoreCase("mana")){
                if (args.length > 1) {
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p == null) {
                        player.sendMessage(ChatColor.RED + "There is no player named " + args[1]);
                        return true;
                    }
                    PlayerData pd = PlayerData.getPlayerData(p);
                    pd.mana = playerData.getMaxMana();
                }else {
                    playerData.mana = playerData.getMaxMana();
                }
            }else if(args[0].equalsIgnoreCase("spells")){
                if (args.length == 1)
                    SpellHandler.openSpellsSelectMenu(player);
                else if (args.length == 2){
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p == null){
                        player.sendMessage(ChatColor.RED + "There is no player named " + args[1]);
                        return true;
                    }
                    SpellHandler.openSpellsSelectMenu(p);
                }
            }
            else if (args[0].equalsIgnoreCase("restore")){
                Player p = Bukkit.getPlayer(args[1]);
                if (p == null){
                    player.sendMessage(ChatColor.RED + "There is no player named " + args[1]);
                    return true;
                }
                PlayerData pd = PlayerData.getPlayerData(p);

                pd.point = 0;
                pd.level = 0;

                for (String adv : SpellHandler.getAdvancementPoints().keySet()){
                    Advancement a = getAdvancement(adv);

                    if(a == null){
                        Bukkit.broadcastMessage("Its null!");
                        continue;
                    }

                    AdvancementProgress progress = p.getAdvancementProgress(a);

                    if (progress.isDone()){
                        pd.point += SpellHandler.getAdvancementAmount(adv);
                    }
                }

                p.sendMessage(ChatColor.GREEN + "Advancement points has been restored");
                player.sendMessage(ChatColor.GREEN + "Advancement points has been restored for " + p.getName());
            }
            else if (args[0].equalsIgnoreCase("test")){
                PetsHandler.spawn(player);
            }
        }
        return true;
    }

    public static Advancement getAdvancement(String name) {
        Iterator<Advancement> it = Bukkit.getServer().advancementIterator();
        // gets all 'registered' advancements on the server.
        while (it.hasNext()) {
            // loops through these.
            Advancement a = it.next();
            if (a.getKey().toString().contains(name)) {
                //checks if one of these has the same name as the one you asked for. If so, this is the one it will return.
                return a;
            }
        }
        return null;
    }
}
