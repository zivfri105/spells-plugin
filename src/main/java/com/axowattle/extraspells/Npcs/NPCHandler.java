package com.axowattle.extraspells.Npcs;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.PlayerInteractManager;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPCHandler {

    private static final List<NPC> NPCs = new ArrayList<>();



    public static void addNPC(NPC npc){
        NPCs.add(npc);
    }

    public static void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        for (NPC npc : NPCs) {
            npc.sendSpawnPacket(player);
        }
    }

    public static void onTick(){
        for (NPC npc : NPCs) {
            npc.tick();
        }
    }

    public static NPC getNPC(int amount){
        return NPCs.get(amount);
    }

}
