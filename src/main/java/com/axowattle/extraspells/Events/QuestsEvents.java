package com.axowattle.extraspells.Events;

import com.axowattle.extraspells.Quests.Quest;
import com.axowattle.extraspells.Quests.QuestsHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class QuestsEvents implements Listener {
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event){
        for (Quest quest : QuestsHandler.quests){
            quest.onChunkLoad(event);
        }
    }

}
