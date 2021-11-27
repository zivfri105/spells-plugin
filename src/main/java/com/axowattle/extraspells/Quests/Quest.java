package com.axowattle.extraspells.Quests;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockChange;
import net.minecraft.network.protocol.game.PacketPlayOutMultiBlockChange;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Quest {

    private final int level;
    private final int distance;
    private final int dungeonSize;
    private final int xpReceive;

    private List<ChallengedPlayer> challengedPlayers;

    public Quest(int level,int distance,int dungeonSize,int xpReceive){
        this.level = level;
        this.distance = distance;
        this.dungeonSize = dungeonSize;
        this.xpReceive = xpReceive;
        challengedPlayers = new ArrayList<>();
    }

    public Location challengePlayer(Player player){
        ChallengedPlayer challengedPlayer = new ChallengedPlayer(player,distance,this);
        challengedPlayers.add(challengedPlayer);
        return challengedPlayer.targetLoc;
    }


    public void onChunkLoad(ChunkLoadEvent event){
        for (ChallengedPlayer player :challengedPlayers){
            player.showBlocks();
        }
    }

    public int getDungeonSize() {
        return dungeonSize;
    }

    public boolean isViable(Location location, double dungeonSize){
        for (ChallengedPlayer challengedPlayer : challengedPlayers){
            if (challengedPlayer.generatedDungeon){
                if (location.distance(challengedPlayer.dungeonLoc) < this.dungeonSize + dungeonSize){
                    return false;
                }
            }
        }
        return true;
    }


    public class ChallengedPlayer{
        public Player player;
        public Quest quest;
        public Location targetLoc;
        public Block[] teleportBlocks;
        public Map<Vector,BlockData> dungeonBlocks;
        public int height;
        public boolean generatedDungeon;
        public Location dungeonLoc;

        public ChallengedPlayer(Player player,int distance,Quest quest){
            Random random = new Random();
            this.player = player;
            this.quest = quest;
            height = -1;
            targetLoc = player.getLocation().clone();
            targetLoc.setX(random.nextInt(distance * 2)-distance);
            targetLoc.setY(0);
            targetLoc.setZ(random.nextInt(distance * 2)-distance);
        }

        public void showBlocks(){
            if (targetLoc.getChunk().isLoaded()) {
                if (height == -1) {
                    int i = QuestsHandler.getMainWorld().getMaxHeight();
                    Location heightBlock = targetLoc.clone();
                    heightBlock.setY(i);

                    while (i > 2 & !heightBlock.getBlock().getType().isSolid()){
                        i--;
                        heightBlock.setY(i);
                    }

                    teleportBlocks = new Block[]{
                            heightBlock.clone().add(0,1,0).getBlock(),
                            heightBlock.clone().add(0,2,0).getBlock(),
                            heightBlock.clone().add(0,3,0).getBlock()};

                    this.height = i;
                }

                targetLoc.setY(height);

                for (Vector offset : QuestsHandler.blocks.keySet()) {
                    Material material = QuestsHandler.blocks.get(offset);
                    BlockData block = material.createBlockData();
                    Location location = targetLoc.clone().add(offset);
                    if (location.getChunk().isLoaded()){
                        player.sendBlockChange(location,block);
                    }

                }
            }
        }


    }
}
