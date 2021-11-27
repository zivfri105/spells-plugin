package com.axowattle.extraspells.ConfigManagers;

import com.axowattle.extraspells.ExtraSpells;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    private File file;
    protected FileConfiguration customFile;
    protected String name;
    protected Plugin plugin;
    public ConfigFile(Plugin plugin, String name){
        this.name = name;
        this.plugin = plugin;
        setup();
        save();
    }
    public void setup(){
        file = new File(plugin.getDataFolder(),name+".yml");
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){

            }
        }
        customFile= YamlConfiguration.loadConfiguration((file));
    }
    public FileConfiguration get (){
        return customFile;
    }

    public void save (){
        try {
            customFile.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save file.");
        }
    }

    public void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
