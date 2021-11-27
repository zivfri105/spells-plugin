package com.axowattle.extraspells.Npcs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Ghost extends NPC{
    private static String ghostTextures = "ewogICJ0aW1lc3RhbXAiIDogMTU5MTQzNjkwMTk3NiwKICAicHJvZmlsZUlkIiA6ICI5MWZlMTk2ODdjOTA0NjU2YWExZmMwNTk4NmRkM2ZlNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJoaGphYnJpcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xYzNjMGE1OTkxZWNiZGZlMGNiNDQ2MGYyYTAyOTIxYzkzYTViMDVjYmNkZTZhNmFhYjYwN2YwNGM5MjI5YTk4IgogICAgfQogIH0KfQ==";
    private static String ghostSignature = "JK0QyvHkAOt5i8zJdZ+wNeZ/IbPZKahkIRtX3/tjFRd5mExMpCeHNcbZo4C2YEUuPshRvyyaTTvrpwyNVtoZIfzUqlB5WeFXesUatECh/rM9X/JsuPbV38dJGgjEHgWM+Oa90LbOg2ycUwRJJniM1UQPTrByLIF8WKlVK6/fQHB2sa0civnXrQOxvobjkaEUlHdno/ggHKeIK5AUHpTSyL0LV5QJHeUmCl5Ixd6f7M58OOZIFN8RRmjANkPsXzqgrYxDK56LMJcbANzduo4IrK2OJPVOStqv1zQvkn9PpGRd8yPnzZX/vYJYIoHryKj/zunGq4pc//TXh/JMYSWGxrFb4alrePhPN2G+O85vBnawpEglNXYmhddC0CyxchPrj1gg/qKMLQ2oRVEJlBuiBXHbvjuxG00uGvYQmpWCQ9I9ePkmySus808ynXseaM8ToDy7b0LOMimyr4c6c7tKE7h9NIncOPWFWXpEwmpL/k8aH7HxA3KacxjWASnVIYt78gCyBlkcnCUNj1e8LFgUfEFoN/nMy97EE+OCyTwrSkJ5nOTIKF5BB+qsR1hCg4o97LEGjNfRaWlmgcLhhqQmrRGhOuoylTwVCvdHKHLDuD2/DJ35TPtEkp8zMCpxggG2UmaNFShSSnSDasvuJZo4S0fauvMxPJwRZ9NXBCplkNY=";

    private Player player;

    public Ghost(Player player) {
        super(player.getLocation(), ghostTextures,ghostSignature);
        this.player = player;
    }

    @Override
    public void tick() {
        if (this.pathfinder != null)
            pathfinder.updateGirdBlocks();
        setTarget(player.getLocation());
        Location target = pathfinder.getNextLoc(location);

        if (target != null) {
            Vector diff = target.toVector().subtract(location.toVector());
            moveNpc(diff.getX() * .1, diff.getY() * .1, diff.getZ() * .1);
        }
    }
}
