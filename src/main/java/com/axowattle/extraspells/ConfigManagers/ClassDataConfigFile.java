package com.axowattle.extraspells.ConfigManagers;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

public class ClassDataConfigFile extends ConfigFile{
    protected static final String tagID = "_tags";
    protected Map<String,Class<?>> storedTypes;
    public ClassDataConfigFile(Plugin plugin,String name, Map<String,Class<?>> storedTypes){
        super(plugin,name);
        this.storedTypes = storedTypes;
    }

    public void setValues(String id, Object ... values){
        if (values.length != storedTypes.size()) throw new IllegalArgumentException();
        if (customFile.contains(id)) {
            ConfigurationSection playerSection = customFile.getConfigurationSection(id);
            for (int i =0;i<values.length;i++) {
                String name = (String) storedTypes.keySet().stream().toArray()[i];
                Object value = values[i];

                playerSection.set(name,storedTypes.get(name).cast(value));
            }
        }else {
            ConfigurationSection playerSection = customFile.createSection(id);
            for (int i =0;i<values.length;i++) {
                String name = (String) storedTypes.keySet().stream().toArray()[i];
                Object value = values[i];


                playerSection.addDefault(name,storedTypes.get(name).cast(value));
            }
        }
    }

    public<T> T getValue(String id,String valueName) {
        if (!customFile.contains(id)) return null;
        ConfigurationSection playerData = customFile.getConfigurationSection(id);
        if (playerData.contains(valueName)) throw new NoSuchElementException();
        return (T) playerData.get(valueName);
    }

    public void setValue(String id,String valueName,Object object){
        if (!customFile.contains(id)) return;
        ConfigurationSection playerData = customFile.getConfigurationSection(id);
        playerData.set(valueName,storedTypes.get(name).cast(customFile.get(valueName)));
    }

    public void setTag(String id,String tag){
        if (!customFile.contains(id)) throw new NoSuchElementException();
        ConfigurationSection tagEntry = customFile.getConfigurationSection(id);
        if (tagEntry.contains(tagID)){
            String tags = tagEntry.getString(tagID);
            tags += "," + tag;
            tagEntry.set(tagID, tags);
        }else {
            tagEntry.addDefault(tagID,tag);
        }
    }

    public boolean containsTag(String id,String tag){
        return Arrays.asList(getTags(id)).contains(tag);
    }

    public String[] getTags(String id){
        if (!customFile.contains(id)) throw new NoSuchElementException();
        ConfigurationSection tagEntry = customFile.getConfigurationSection(id);
        if (!tagEntry.contains(tagID)) throw new NoSuchElementException();
        return tagEntry.getString(tagID).split(",");
    }

    public boolean contains(Player player){
        return customFile.contains(player.getUniqueId().toString());
    }
}
