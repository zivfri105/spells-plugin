package com.axowattle.extraspells.Spells.Cleric;

import com.axowattle.extraspells.Spells.Spell;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WitherThornsSpell extends Spell {

    private static List<String> affectedPlayers = new ArrayList<>();

    public WitherThornsSpell(){
        super("Wither Thorns", "&7The next time someone attacks you they get a wither effect.", 2, 100);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        affectedPlayers.add(caster.getName());
        return true;
    }


    public static void onEntityDamage (EntityDamageByEntityEvent event){
        if(affectedPlayers.contains(event.getEntity().getName()) & event.getEntity() instanceof Player & event.getDamager() instanceof LivingEntity){
            affectedPlayers.remove(event.getEntity().getName());
            ((LivingEntity) event.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER,80,1));
        }
    }
}
