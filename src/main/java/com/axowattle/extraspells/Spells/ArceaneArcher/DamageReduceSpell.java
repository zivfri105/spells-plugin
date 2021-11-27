package com.axowattle.extraspells.Spells.ArceaneArcher;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class DamageReduceSpell extends Spell {

    private static List<String> affectedPlayers = new ArrayList<>();

    public DamageReduceSpell(){
        super("Damage Reduce", "&7The next time someone attacks\nthe damage will be cut in half.", 2, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        affectedPlayers.add(caster.getName());
        caster.sendMessage(ChatColor.DARK_PURPLE + "You just got double defence for the next attack.");
        return true;
    }


    public static void onEntityDamage (EntityDamageEvent event){
        if(affectedPlayers.contains(event.getEntity().getName()) & event.getEntity() instanceof Player){
            affectedPlayers.remove(event.getEntity().getName());
            event.setDamage(event.getDamage() / 2);
        }
    }
}
