package com.axowattle.extraspells.ConfigManagers;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RegionConfigFile extends ClassDataConfigFile{
    public static final String startPos = "start-pos";
    public static final String endPos = "end-pos";

    public RegionConfigFile(Plugin plugin, String name, Map<String, Class<?>> storedTypes) {
        super(plugin, name, storedTypes);
        this.storedTypes.put(startPos,Location.class);
        this.storedTypes.put(endPos,Location.class);
    }

    public void setValues(String name,Location startLoc,Location endLoc, Object... values) {
        List<Object> values_list = Arrays.asList(values);
        values_list.add(startLoc);
        values_list.add(endLoc);
        values = values_list.toArray();
        super.setValues(name, values);
    }

    public <T> List<T> getValues(Location loc,String value) {
        List<T> values = new ArrayList<>();
        for (String regionKey : customFile.getKeys(false)) {

        }
        return values;
    }

    @Override
    public void setValues(String id, Object... values) {
        throw new UnsupportedOperationException();
    }
}
