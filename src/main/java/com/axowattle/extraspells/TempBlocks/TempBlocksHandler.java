package com.axowattle.extraspells.TempBlocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import org.graalvm.compiler.lir.LIRInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TempBlocksHandler {
    private static List<TempBlocks> tempBlocksList = new ArrayList<>();


    public static void resetBlocks(){
        tempBlocksList.removeIf(TempBlocks::resetIfCan);
    }

    public static void addBlocks(Location startPoint,List<Vector> positions, List<Material> materials,long time){
        List<Location> locations = new ArrayList<>();
        for (Vector location : positions){
            locations.add(startPoint.clone().add(location));
        }

        tempBlocksList.add(new TempBlocks(time,locations,materials));
    }

    public static void addBlocks(Location startPoint, Map<Vector,Material> blocks, long time){

        List<Location> locations= new ArrayList<>();
        List<Material> materials = new ArrayList<>();

        for (Vector location : blocks.keySet()){
            materials.add(blocks.get(location));
            locations.add(startPoint.clone().add(location));
        }

        tempBlocksList.add(new TempBlocks(time,locations,materials));
    }
}
