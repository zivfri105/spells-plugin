package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.ArrowEffects.ArrowHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.List;

public class ProjectileLaunch implements Listener {
    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event){
        if (event.getEntity().getShooter() instanceof Player) {
            ArrowHandler.setArrow((Player) event.getEntity().getShooter(), event.getEntity());
        }
    }
}
