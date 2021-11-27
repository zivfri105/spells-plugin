package com.axowattle.extraspells.Handlers;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class MethodHandler {

    public Location getRaycastLoc(Location loc, Vector direction,double distance){
        direction = direction.clone().normalize();
        loc = loc.clone();
        Location originLoc = loc.clone();

        Location targetLoc = loc;

        while (originLoc.distance(loc) < distance){
            if (targetLoc.getBlock() != null)
                if (!targetLoc.getBlock().isPassable()) break;
            originLoc.add(direction);
            targetLoc = originLoc.clone();
        }

        return targetLoc;
    }

    public Location getRaycastLoc(Location startLoc,Location endLoc){
        endLoc = endLoc.clone();
        Vector dir = endLoc.subtract(startLoc).toVector();
        return getRaycastLoc(startLoc,dir, dir.distance(new Vector(0,0,0)));
    }

}
