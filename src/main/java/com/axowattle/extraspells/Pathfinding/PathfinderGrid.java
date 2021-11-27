package com.axowattle.extraspells.Pathfinding;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.w3c.dom.Node;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PathfinderGrid {

    private static final int timeout = 1_000;

    private PathfinderTile[][][] grid;
    private List<PathfinderTile> path;
    private Location originLoc;
    private int gridSizeX;
    private int gridSizeY;
    private int gridSizeZ;

    private int startPosX;
    private int startPosY;
    private int startPosZ;

    private int endPosX;
    private int endPosY;
    private int endPosZ;

    public PathfinderGrid(Location location,int gridSizeX,int gridSizeY,int gridSizeZ){
        originLoc = location.clone();
        if (originLoc.getY() < 0) originLoc.setY(0);
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.gridSizeZ = gridSizeZ;
        this.generateGrid();
    }

    public PathfinderGrid(Location location, int gridSizeX, int gridSizeY, int gridSizeZ, int startPosX, int startPosY, int startPosZ,int endPosX,int endPosY,int endPosZ){
        originLoc = location;
        if (originLoc.getY() < 0) originLoc.setY(0);
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.gridSizeZ = gridSizeZ;

        this.startPosX = startPosX;
        this.startPosY = startPosY;
        this.startPosZ = startPosZ;

        this.endPosX = endPosX;
        this.endPosY = endPosY;
        this.endPosZ = endPosZ;

        this.generateGrid();

        this.findPath();
    }

    public void updateGirdBlocks(){
        for (int x = 0; x < gridSizeX;x++) {
            for (int y = 0; y < gridSizeY; y++) {
                for (int z = 0; z < gridSizeZ; z++) {
                    grid[x][y][z].getIsWalkable();
                }
            }
        }
    }

    private void findPath(){
        PathfinderTile startTile = grid[startPosX][startPosY][startPosZ];
        PathfinderTile endTile = grid[endPosX][endPosY][endPosZ];


        List<PathfinderTile> openSet = new ArrayList<>();
        Set<PathfinderTile> closedSet = new HashSet<>();
        openSet.add(startTile);

        int times = 0;
        while (openSet.size() > 0 && times < timeout){
            PathfinderTile currentTile = openSet.get(0);
            for(int i = 1;i < openSet.size();i++){
                if (openSet.get(i).fCost() < currentTile.fCost() || openSet.get(i).fCost() == currentTile.fCost() && openSet.get(i).hCost < currentTile.hCost ){
                    currentTile = openSet.get(i);
                }
            }

            openSet.remove(currentTile);
            closedSet.add(currentTile);

            if (currentTile == endTile){
                retracePath();
                return;
            }

            for(PathfinderTile neighbour : getNeighbours(currentTile)){
                if (!neighbour.isWalkable || closedSet.contains(neighbour)){
                    continue;
                }

                double newMovementCostToNeighbour = currentTile.gCost + getDistance(currentTile,neighbour);
                if(newMovementCostToNeighbour < neighbour.gCost || !openSet.contains(neighbour)){
                    neighbour.gCost = newMovementCostToNeighbour;
                    neighbour.hCost = getDistance(neighbour,endTile);
                    neighbour.parent = currentTile;




                    if (!openSet.contains(neighbour))
                        openSet.add(neighbour);
                }
            }
            times++;
        }
    }

    public void retracePath(){


        PathfinderTile startTile = grid[startPosX][startPosY][startPosZ];
        PathfinderTile endTile = grid[endPosX][endPosY][endPosZ];

        PathfinderTile currentTile = endTile;

        path = new ArrayList<>();
        while (currentTile != startTile){
            path.add(currentTile);
            currentTile = currentTile.parent;
        }
        Collections.reverse(path);
    }

    public Location getNextLoc(Location loc){
        /*PathfinderTile endTile = grid[endPosX][endPosY][endPosZ];

        int[] pos = globalToLocal(loc);
        PathfinderTile currentTile = grid[pos[0]][pos[1]][pos[2]];
        if (path.contains(currentTile) && currentTile != endTile){
            PathfinderTile nextTile = path.get(path.indexOf(currentTile) + 1);
            sendBlockChange(localToGlobal(nextTile.gridPosX,nextTile.gridPosY,nextTile.gridPosZ),Material.RAIL);
            return localToGlobal(nextTile.gridPosX,nextTile.gridPosY,nextTile.gridPosZ);
        }
        return null;*/
        PathfinderTile nextTile = path.get(2);
        return localToGlobal(nextTile.gridPosX,nextTile.gridPosY,nextTile.gridPosZ);
    }

    public Location getFirstLoc(){
        PathfinderTile nextTile = path.get(0);


        return localToGlobal(nextTile.gridPosX,nextTile.gridPosY,nextTile.gridPosZ);
    }


    public double getDistance(PathfinderTile tileA,PathfinderTile tileB){
        double distanceX = Math.abs(tileA.gridPosX - tileB.gridPosX);
        double distanceY = Math.abs(tileA.gridPosY - tileB.gridPosY);
        double distanceZ = Math.abs(tileA.gridPosZ - tileB.gridPosZ);

        List<Double> distances = Arrays.asList(distanceX,distanceY,distanceZ);
        Collections.sort(distances);

        return 1.7 * distances.get(0) + 1.4 * (distances.get(1) - distances.get(0) + (distances.get(2) - distances.get(1)));

    }

    public List<PathfinderTile> getNeighbours(PathfinderTile tile){
        List<PathfinderTile> neighbours = new ArrayList<>();

        for (int x = -1; x <= 1;x++){
            for (int y = -1; y <= 1;y++){
                for (int z = -1; z <= 1;z++){
                    if (x==0 && y==0 && z==0) continue;

                    int checkX = tile.gridPosX + x;
                    int checkY = tile.gridPosY + y;
                    int checkZ = tile.gridPosZ + z;

                    if (checkX >= 0 && checkX < gridSizeX && checkY >= 0 && checkY < gridSizeX && checkZ >= 0 && checkZ < gridSizeX){
                        neighbours.add(grid[checkX][checkY][checkZ]);
                    }
                }
            }
        }

        return neighbours;
    }

    public Location localToGlobal(int x,int y, int z){
        return originLoc.clone().add(x,y,z);
    }
    public int[] globalToLocal(Location loc){
        loc = loc.clone();
        loc.subtract(originLoc);
        return new int[]{loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()};
    }

    private void generateGrid(){
        grid = new PathfinderTile[gridSizeX][gridSizeY][gridSizeZ];

        for (int x = 0;x < grid.length;x++){
            for (int y = 0;y < grid.length;y++){
                for (int z = 0;z < grid.length;z++){
                    PathfinderTile tile = new PathfinderTile(this,x,y,z);
                    grid[x][y][z]= tile;

                }
            }
        }
    }

    public boolean isInGrid(Location loc){
        int[] pos = globalToLocal(loc);
        return pos[0] < gridSizeX && pos[1] < gridSizeY && pos[2] < gridSizeZ;
    }

    public void tracePath(Location startLoc,Location endLoc){
        int[] startPos = globalToLocal(startLoc);
        int[] endPos = globalToLocal(endLoc);
        startPosX = startPos[0];
        startPosY = startPos[1];
        startPosZ = startPos[2];
        endPosX = endPos[0];
        endPosY = endPos[1];
        endPosZ = endPos[2];

        findPath();
    }

}
