package com.axowattle.extraspells.Spells.Druid;

import com.axowattle.extraspells.Spells.PlayerData;
import com.axowattle.extraspells.Spells.Spell;
import com.axowattle.extraspells.Spells.SpellHandler;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BuffSpell extends Spell {
    public BuffSpell(){
        super("Buff","&7Buffs you.",1,50);
    }


    @Override
    public boolean onSpellCast(Player caster) {
        PlayerData playerData = PlayerData.getPlayerData(caster);
        int food = caster.getFoodLevel() + 6;
        float saturation = caster.getSaturation() + 6;

        if (food > 20) food = 20;
        if(saturation > 20) saturation = 20;

        caster.setFoodLevel(food);
        caster.setSaturation(saturation);
        caster.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,100,1));
        caster.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,200,1));
        caster.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,200,1));
        return true;
    }
}
