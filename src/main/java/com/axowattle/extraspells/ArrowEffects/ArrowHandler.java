package com.axowattle.extraspells.ArrowEffects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrowHandler {

    private static int id;

    private static List<ArrowEffect> arrowEffects = new ArrayList<>(Arrays.asList(new ArrowEffectDamage(),new ArrowEffectProtection(),new ArrowHealthSteal(),new ArrowEffectSwitch()));
    private static HashMap<Projectile,HashMap<Integer,Integer>> arrowEffectsMap = new HashMap<>();
    private static HashMap<Player,HashMap<Integer,Integer>> playerEffectsMap = new HashMap<>();

    public static int getId(){
        id++;
        if (id > 1000) id = 0;
        return id-1;
    }

    public static ArrowEffect getArrowEffect(int arrowId) {
        for (ArrowEffect arrowEffect : arrowEffects){
            if (arrowEffect.getId() == arrowId)
                return arrowEffect;
        }
        return null;
    }

    public static boolean addEffectToPlayer(Player player,ArrowEffect effect){
        if (!playerEffectsMap.containsKey(player)){
            playerEffectsMap.put(player,new HashMap<>());
        }

        if (playerEffectsMap.get(player).containsKey(effect.getId())) {
            if (playerEffectsMap.get(player).get(effect.getId()) <= effect.getMaxStackSize()) {
                int value = playerEffectsMap.get(player).get(effect.getId());
                playerEffectsMap.get(player).put(effect.getId(), value + 1);
            }else{
                player.sendMessage(ChatColor.RED + "Spells don't stack grater then " + effect.getMaxStackSize() +".");
                return false;
            }
        }else {
            playerEffectsMap.get(player).put(effect.getId(), 1);
        }
        return true;
    }

    public static void setArrow(Player shooter,Projectile arrow){
        if(playerEffectsMap.containsKey(shooter)){
            arrowEffectsMap.put(arrow,playerEffectsMap.get(shooter));
            playerEffectsMap.remove(shooter);
            for (Integer effectId : arrowEffectsMap.get(arrow).keySet()) {
                getArrowEffect(effectId).onArrowLaunch(arrow);
            }
        }
    }

    public static ArrowEffect getArrowEffect(String arrowName) {
        for (ArrowEffect arrowEffect : arrowEffects){
            if (arrowEffect.getName().equals(arrowName))
                return arrowEffect;
        }
        return null;

    }


    public static void moveAllArrows(){
        for(Projectile arrow : arrowEffectsMap.keySet()){
            for (Integer effectId : arrowEffectsMap.get(arrow).keySet()) {
                getArrowEffect(effectId).onArrowMove(arrow);
            }
        }
    }

    public static void arrowHitGround(Projectile arrow, Block block){
        for (Integer effectId : arrowEffectsMap.get(arrow).keySet()) {
            getArrowEffect(effectId).onArrowHitGround(arrow, block);
        }

        arrowEffectsMap.remove(arrow);

    }

    public static HashMap<Integer, Integer> getPlayerEffectsMap(Player player) {
        return playerEffectsMap.get(player);
    }

    public static void arrowHitEntity(Projectile arrow, EntityDamageByEntityEvent event){
        if (arrowEffectsMap.containsKey(arrow)) {
            for (Integer effectId : arrowEffectsMap.get(arrow).keySet()) {
                getArrowEffect(effectId).onArrowHitEntity(arrow,arrowEffectsMap.get(arrow).get(effectId),event);
            }
            arrowEffectsMap.remove(arrow);
        }
    }
}
