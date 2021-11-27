package com.axowattle.extraspells;

import com.axowattle.extraspells.Events.*;
import com.axowattle.extraspells.Spells.Sorcerer.InvisibilitySpell;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class ExtraSpells extends JavaPlugin {

    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        SpellHandler.initialize();


        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BedAwake(),this);
        pm.registerEvents(new InventoryClose(),this);
        pm.registerEvents(new InventoryClick(),this);
        pm.registerEvents(new PlayerJoin(),this);
        pm.registerEvents(new PlayerAdvancementDone(),this);
        pm.registerEvents(new PlayerInteract(),this);
        pm.registerEvents(new InventoryOpen(),this);
        pm.registerEvents(new EntityDamageByEntity(),this);
        pm.registerEvents(new ProjectileLaunch(),this);
        pm.registerEvents(new EntityDamaged(),this);
        pm.registerEvents(new BlockPlace(),this);
        pm.registerEvents(new ProjectileHit(),this);
        pm.registerEvents(new EntitySpawn(),this);
        pm.registerEvents(new PassiveAbilitiesEvents(),this);
        pm.registerEvents(new QuestsEvents(),this);
        pm.registerEvents(new FireballHit(),this);
        pm.registerEvents(new PlayerQuit(),this);
        pm.registerEvents(new PlayerInteractEntity(),this);
        pm.registerEvents(new ArmorEvents(),this);

        getCommand("spell").setExecutor(new SpellCommand());
    }

    @Override
    public void onDisable() {
        SpellHandler.disable();
    }

    public static Plugin getInstance() {
        return instance;
    }
}
