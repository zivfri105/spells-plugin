package com.axowattle.extraspells.Npcs;

import com.axowattle.extraspells.Pathfinding.PathfinderGrid;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMoveThroughVillage;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class NPC {
    private static final int maxGridSize = 50;

    protected EntityPlayer instance;
    protected Location location;
    protected String textures;
    protected String signature;
    protected boolean isAlive;
    protected PathfinderGrid pathfinder;
    private Location target;

    public NPC(Location location,String textures,String signature){
        this.textures = textures;
        this.signature = signature;
        this.location = location;
        this.isAlive = false;

        this.spawnNPC(location);
    }

    public NPC(String username,Location location){
        String[] skin = getSkin(username);
        if (skin != null) {
            this.textures = skin[0];
            this.signature = skin[1];
        }
        this.location = location;
        this.isAlive = false;

        this.spawnNPC(location);

        
    }

    public final void setTarget(Location loc){
        this.target = loc;

        if (pathfinder == null || !pathfinder.isInGrid(loc)){
            Vector offset = loc.toVector().subtract(location.toVector());
            if (Math.abs(offset.getX()) > maxGridSize || Math.abs(offset.getY()) > maxGridSize || Math.abs(offset.getZ()) > maxGridSize){
                double maxValue = Math.max(Math.max(offset.getX(),offset.getY()),offset.getZ());
                offset.divide(new Vector(maxValue,maxValue,maxValue));
            }
            Location originLoc = location.clone().subtract(new Vector(maxGridSize/2,maxGridSize/2,maxGridSize/2));
            pathfinder = new PathfinderGrid(originLoc,maxGridSize,maxGridSize,maxGridSize);
        }

        pathfinder.tracePath(location,target);
    }


    private void spawnNPC(Location location){
        if (!isAlive) {
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "Ghost");
            instance = new EntityPlayer(server, world, gameProfile);
            instance.getProfile().getProperties().removeAll("textures");
            instance.getProfile().getProperties().put("textures", new Property("textures", textures, signature));
            Location loc = location;
            instance.setPosition(loc.getX(), loc.getY(), loc.getZ());

            for (Player player : Bukkit.getOnlinePlayers()) {
                sendSpawnPacket(player);
            }

            NPCHandler.addNPC(this);
            this.isAlive = true;
        }
    }

    public final void despawn(){
        if (isAlive) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendDespawnPacket(player);
            }
            isAlive = false;
        }
    }

    private void sendDespawnPacket(Player player) {
        PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
        connection.sendPacket(new PacketPlayOutEntityDestroy(instance.getId()));
    }

    public void lookAt(Location loc){
        EulerAngle eulerAngle = directionToEuler(loc.subtract(location).toVector().normalize());
        for (Player player : Bukkit.getOnlinePlayers()){
            sendLookDirectionPacket(player,eulerAngle.getY(),eulerAngle.getX());
        }
    }

    public void tick(){

    }

    private void sendLookDirectionPacket(Player player, double yaw, double pitch) { // might bee needed to change to float
        PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(instance, (byte)(yaw * 256 / 360)));
        connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(instance.getId(), (byte)(yaw * 256 / 360), (byte)(pitch * 256 / 360), true));
    }

    public void moveNpc(double x, double y, double z){
        location.add(x,y,z);
        for(Player player : Bukkit.getOnlinePlayers()){
            sendMoveEntityPacket(player,x,y,z);
        }
    }

    private void sendMoveEntityPacket(Player player, double x, double y, double z) {
        ((CraftPlayer)player).getHandle().b.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMove(instance.getId(), (short)(x * 4096), (short)(y * 4096), (short)(z * 4096), true));
    }

    public void sendSpawnPacket(Player player)
    {
        PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a,instance));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(instance));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(instance,(byte) (instance.getYRot() * 256 / 360)));
    }

    protected static String[] getSkin(String username) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://api.ashcon.app/mojang/v2/user/%s", username)).openConnection();
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                reader.lines().forEach(lines::add);

                String reply = String.join(" ",lines);
                int indexOfValue = reply.indexOf("\"value\": \"");
                int indexOfSignature = reply.indexOf("\"signature\": \"");
                String skin = reply.substring(indexOfValue + 10, reply.indexOf("\"", indexOfValue + 10));
                String signature = reply.substring(indexOfSignature + 14, reply.indexOf("\"", indexOfSignature + 14));

                return new String[]{skin,signature};

            }

            else {
                Bukkit.getConsoleSender().sendMessage("Connection could not be opened when fetching player skin (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EntityPlayer getInstance() {
        return instance;
    }

    private EulerAngle directionToEuler(Vector dir) {
        double xzLength = Math.sqrt(dir.getX()*dir.getX() + dir.getZ()*dir.getZ());
        double pitch = Math.atan2(xzLength, dir.getY()) - Math.PI / 2;
        double yaw = -Math.atan2(dir.getX(), dir.getZ());
        return new EulerAngle(pitch, yaw, 0);
    }
}
