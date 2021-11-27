package com.axowattle.extraspells.Handlers;

import java.util.ArrayList;
import java.util.List;

public class SphereHandler {
    private static List<Sphere> spheres = new ArrayList<>();

    public static void addSphere(Sphere sphere){
        spheres.add(sphere);
    }

    public static void tick(){
        for (Sphere sphere : spheres){
            sphere.onSphereMove();
            if (!sphere.getLocation().getChunk().isLoaded())spheres.remove(sphere);
        }

        spheres.removeIf(sphere -> !sphere.getLocation().getChunk().isLoaded());
        spheres.removeIf(sphere -> 255 < sphere.getLocation().getY() | sphere.getLocation().getY() < 0);
        spheres.removeIf(sphere -> sphere.getAmountAlive() < 1);

    }

}
