package com.axowattle.extraspells.Quests;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.*;

public class QuestsHandler {
    public static Map<Vector, Material> blocks = new HashMap<>();
    public static List<Quest> quests = Arrays.asList(new Quest(1,1000,10,1));

    public static void initialize(){
        blocks.put(new Vector(0,0,0), Material.SMOOTH_STONE);

        blocks.put(new Vector(1,1,0), Material.POLISHED_ANDESITE_SLAB);
        blocks.put(new Vector(-1,1,0), Material.POLISHED_ANDESITE_SLAB);
        blocks.put(new Vector(0,1,1), Material.POLISHED_ANDESITE_SLAB);
        blocks.put(new Vector(0,1,-1), Material.POLISHED_ANDESITE_SLAB);

        blocks.put(new Vector(1,1,1), Material.SMOOTH_STONE_SLAB);
        blocks.put(new Vector(-1,1,1), Material.SMOOTH_STONE_SLAB);
        blocks.put(new Vector(1,1,-1), Material.SMOOTH_STONE_SLAB);
        blocks.put(new Vector(-1,1,-1), Material.SMOOTH_STONE_SLAB);

        blocks.put(new Vector(1,2,1), Material.SMOOTH_STONE);
        blocks.put(new Vector(-1,2,1), Material.SMOOTH_STONE);
        blocks.put(new Vector(1,2,-1), Material.SMOOTH_STONE);
        blocks.put(new Vector(-1,2,-1), Material.SMOOTH_STONE);

        blocks.put(new Vector(1,3,1), Material.SMOOTH_STONE_SLAB);
        blocks.put(new Vector(-1,3,1), Material.SMOOTH_STONE_SLAB);
        blocks.put(new Vector(1,3,-1), Material.SMOOTH_STONE_SLAB);
        blocks.put(new Vector(-1,3,-1), Material.SMOOTH_STONE_SLAB);

        blocks.put(new Vector(1,4,1), Material.SMOOTH_STONE);
        blocks.put(new Vector(-1,4,1), Material.SMOOTH_STONE);
        blocks.put(new Vector(1,4,-1), Material.SMOOTH_STONE);
        blocks.put(new Vector(-1,4,-1), Material.SMOOTH_STONE);

        blocks.put(new Vector(1,4,0), Material.POLISHED_ANDESITE_SLAB);
        blocks.put(new Vector(-1,4,0), Material.POLISHED_ANDESITE_SLAB);
        blocks.put(new Vector(0,4,1), Material.POLISHED_ANDESITE_SLAB);
        blocks.put(new Vector(0,4,-1), Material.POLISHED_ANDESITE_SLAB);

        blocks.put(new Vector(0,1,0), Material.WATER);
        blocks.put(new Vector(0,2,0), Material.WATER);
        blocks.put(new Vector(0,3,0), Material.WATER);
        blocks.put(new Vector(0,4,0), Material.WATER);

    }

    public static World getMainWorld(){
        return Bukkit.getWorld("world");
    }

    public static World getQuestWorld(){
        return Bukkit.getWorld("quests");
    }


    public static Location getFreePosition(Quest quest){
        Random random = new Random();
        int distance = 0;
        Location loc = getQuestWorld().getBlockAt(random.nextInt(distance),0,random.nextInt(distance)).getLocation();
        while (!isViable(loc,quest.getDungeonSize())){
            loc = getMainWorld().getBlockAt(random.nextInt(distance),0,random.nextInt(distance)).getLocation();
            distance += quest.getDungeonSize();
        }
        loc.getChunk().load();


        return loc;
    }

    public static boolean isViable(Location location,double dungeonSize){
        for (Quest quest : quests){
            if (!quest.isViable(location,dungeonSize)) return false;
        }
        return true;
    }

    public static int getSolidHeight(Location location){
        int i = QuestsHandler.getMainWorld().getMaxHeight();
        Location heightBlock = location.clone();
        heightBlock.setY(i);

        while (i > 2 & !heightBlock.getBlock().getType().isSolid()){
            i--;
            heightBlock.setY(i);
        }

        return i;
    }


}
