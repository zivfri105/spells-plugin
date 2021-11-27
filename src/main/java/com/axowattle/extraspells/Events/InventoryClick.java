package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.PlayerClass;
import com.axowattle.extraspells.SpellSettings;
import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.SpellHandler;
import com.axowattle.extraspells.Spells.WandHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.persistence.PersistentDataType;

import java.io.InputStreamReader;
import java.net.URL;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) return;

        switch (PlayerData.getPlayerData(player).inventoryOpened){
            case 1:
                if (!SpellHandler.isSlotAllowed(event.getSlot()) & isViable(event)){
                    if (event.getSlot() == 27 & event.getCurrentItem().getType() == Material.DIAMOND){
                        PlayerData playerData = PlayerData.getPlayerData(player);
                        playerData.level++;
                        playerData.point -= SpellSettings.levelRequirement;
                        player.closeInventory();
                        playerData.openInventory(1);
                        SpellHandler.openSpellsSelectMenu(player);
                    }
                    event.setCancelled(true);
                    player.updateInventory();
                }
                break;
            case 2:
                if (event.getCurrentItem() != null & isViable(event)){
                    PlayerClass playerClass = SpellHandler.getPlayerClass(event.getCurrentItem());
                    PlayerData.getPlayerData(player).playerClass = playerClass;
                    event.setCancelled(true);
                    player.updateInventory();
                    player.closeInventory();
                    player.sendMessage(ChatColor.GREEN + "You are now a " + playerClass.getName() + ".");
                }
                break;
            case 3:
                if (event.getClickedInventory().getType() == InventoryType.CHEST & !WandHandler.isSlotEmpty(event.getSlot())){
                    if (event.getSlot() == 10){
                        player.closeInventory();
                        WandHandler.craftItem(event.getClickedInventory(),player);
                    }
                    event.setCancelled(true);
                    player.updateInventory();
                }
                break;
            case 4:
                if (event.getClickedInventory().getType() == InventoryType.CHEST){
                    if (event.getCurrentItem() != null){
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(SpellHandler.playerNameKey, PersistentDataType.STRING));
                        changeSkin(player,offlinePlayer.getName());
                    }
                }
                event.setCancelled(true);
                player.updateInventory();
                break;
        }
    }

    public static boolean isViable(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!PlayerData.getPlayerData(player).isInInventory(0)) {
            if (event.getClickedInventory().getType() != InventoryType.CHEST | event.getClick() == ClickType.SHIFT_LEFT | event.getClick() == ClickType.SWAP_OFFHAND) {
                event.setCancelled(true);
                player.updateInventory();
                return false;
            }
        }
        return true;
    }

    public static void changeSkin(Player player,String target){
        String[] name = getSkin(player,target);
        Property property = new Property("textures",name[0],name[1]);
        GameProfile profile = ((CraftPlayer) player).getHandle().getProfile();


        for (Player player1 : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player1).getHandle().b;

            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, ((CraftPlayer) player).getHandle()));

            profile.getProperties().removeAll("textures");
            profile.getProperties().put("textures", property);

            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, ((CraftPlayer) player).getHandle()));
        }

        player.setCustomName(target);
        player.setPlayerListName(target);
    }

    private static String[] getSkin(Player player,String name){
        try{
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+name);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();

            URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader2 = new InputStreamReader(url2.openStream());
            JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = property.get("value").getAsString();
            String signature = property.get("signature").getAsString();
            return new String[]{texture,signature};
        }catch (Exception e){
            EntityPlayer p = ((CraftPlayer)player).getHandle();
            GameProfile profile = p.getProfile();
            Property property = profile.getProperties().get("textures").iterator().next();
            String texture = property.getValue();
            String signature = property.getSignature();
            return new String[]{texture,signature};
        }
    }
}
