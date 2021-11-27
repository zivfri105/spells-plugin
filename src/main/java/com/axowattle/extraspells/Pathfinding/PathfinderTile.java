package com.axowattle.extraspells.Pathfinding;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;

public class PathfinderTile {

    private PathfinderGrid grid;
    public int gridPosX;
    public int gridPosY;
    public int gridPosZ;
    public boolean isWalkable;
    public PathfinderTile parent;

    public double gCost; // distance from beginning
    public double hCost; // distance from target

    private Side[] availableSides;

    public PathfinderTile(PathfinderGrid grid,int gridPosX,int gridPosY,int gridPosZ){
        this.grid = grid;
        this.gridPosX = gridPosX;
        this.gridPosY = gridPosY;
        this.gridPosZ = gridPosZ;

        getIsWalkable();
    }

    public void getIsWalkable(){
        Location loc = grid.localToGlobal(gridPosX,gridPosY,gridPosZ);
        isWalkable = !loc.getBlock().getType().isSolid() && loc.clone().add(0,-1,0).getBlock().getType().isSolid() &&
                !loc.clone().add(0,1,0).getBlock().getType().isSolid();
    }

    public double fCost(){
        return gCost + hCost;
    }

    public enum Side{
        Up(new Vector(0,1,0)),Down(new Vector(0,-1,0)),

        East(new Vector(1,0,0)),West(new Vector(-1,0,0)),North(new Vector(0,0,-1)),South(new Vector(0,0,1)),
        EastNorth(new Vector(1,0,-1)),EastSouth(new Vector(1,0,1)),WestNorth(new Vector(-1,0,-1)),WestSouth(new Vector(-1,0,1)),

        UpEast(new Vector(1,1,0)),UpWest(new Vector(-1,1,0)),UpNorth(new Vector(0,1,-1)),UpSouth(new Vector(0,1,1)),
        UpEastNorth(new Vector(1,1,-1)),UpEastSouth(new Vector(1,1,1)),UpWestNorth(new Vector(-1,1,-1)),UpWestSouth(new Vector(-1,1,1)),

        DownEast(new Vector(1,-1,0)),DownWest(new Vector(-1,-1,0)),DownNorth(new Vector(0,-1,-1)),DownSouth(new Vector(0,-1,1)),
        DownEastNorth(new Vector(1,-1,-1)),DownEastSouth(new Vector(1,-1,1)),DownWestNorth(new Vector(-1,-1,-1)),DownWestSouth(new Vector(-1,-1,1));

        public Vector direction;
        private Side(Vector direction){
            this.direction = direction;
        }
    }
}
