package com.axowattle.extraspells.ConfigManagers;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

public class PlayerDataConfigFile extends ClassDataConfigFile{

    public PlayerDataConfigFile(Plugin plugin, String name, Map<String, Class<?>> storedTypes) {
        super(plugin, name, storedTypes);
    }

    public <T> T getValue(Player player, String valueName) {
        return super.getValue(player.getUniqueId().toString(), valueName);
    }

    public void setValues(Player player, Object... values) {
        super.setValues(player.getUniqueId().toString(), values);
    }

    public void setValue(Player player, String valueName, Object object) {
        super.setValue(player.getUniqueId().toString(), valueName, object);
    }

    public String[] getTags(Player player) {
        return super.getTags(player.getUniqueId().toString());
    }

    public void setTag(Player player, String tag) {
        super.setTag(player.getUniqueId().toString(), tag);
    }

    public boolean containsTag(Player player, String tag) {
        return super.containsTag(player.getUniqueId().toString(), tag);
    }
}
