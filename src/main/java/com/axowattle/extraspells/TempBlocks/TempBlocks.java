package com.axowattle.extraspells.TempBlocks;

import com.axowattle.extraspells.Tasks.SpellCaster;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.*;

public class TempBlocks {
    private long timeLong;
    private long timeStart;
    private List<Location> locations;
    private List<Material> materials;
    private List<Material> tempBlocks;


    public TempBlocks(long timeLong, List<Location> blocks, List<Material> tempBlocks){
        this.timeLong = timeLong;
        this.timeStart = SpellCaster.timeFromStart;
        this.tempBlocks = tempBlocks;

        this.locations = blocks;
        this.materials = new ArrayList<>();
        for(int i = 0; i< locations.size();i++){
            materials.add(locations.get(i).getBlock().getType());
            if (!locations.get(i).getBlock().getType().isSolid())
                locations.get(i).getBlock().setType(tempBlocks.get(i));
        }
    }

    public boolean resetIfCan(){
        if (timeStart + timeLong <= SpellCaster.timeFromStart){
            for(int i = 0; i< locations.size();i++){
                if (locations.get(i).getBlock().getType() == tempBlocks.get(i))
                    locations.get(i).getBlock().setType(materials.get(i));
            }
            return true;
        }
        return false;
    }
}
