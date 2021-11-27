package com.axowattle.extraspells.Spells.Sorcerer;

import com.axowattle.extraspells.Spells.Spell;
import com.axowattle.extraspells.Tasks.SpellCaster;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.world.entity.EnumItemSlot;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.mojang.datafixers.util.Pair;

import java.util.*;

public class InvisibilitySpell extends Spell {
    private static Map<Player,Long> invisPlayers = new HashMap<>();

    private static long invisibilityDuration = 200;

    public InvisibilitySpell() {
        super("Invisibility", "Makes you invisible.", 2, 50);
    }

    @Override
    public boolean onSpellCast(Player caster) {
        caster.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, (int) invisibilityDuration, 10), true);
        invisPlayers.put(caster,SpellCaster.timeFromStart);
        return true;
    }

    public static void clearInvisiblePlayersArmor(){

        for (Player player : invisPlayers.keySet()){
            setFakeArmor(player,Arrays.asList(new ItemStack(Material.AIR),new ItemStack(Material.AIR),new ItemStack(Material.AIR),new ItemStack(Material.AIR),new ItemStack(Material.AIR),new ItemStack(Material.AIR)));
            if (SpellCaster.timeFromStart - invisPlayers.get(player) >= invisibilityDuration){
                PlayerInventory inventory = player.getInventory();
                setFakeArmor(player,Arrays.asList(inventory.getItemInMainHand(),inventory.getItemInOffHand(),inventory.getBoots(),inventory.getLeggings(),inventory.getChestplate(),inventory.getHelmet()));
            }
        }

        invisPlayers.keySet().removeIf(key -> SpellCaster.timeFromStart - invisPlayers.get(key) >= invisibilityDuration);
    }

    public static void removeInvisibility(Player player){
        if (invisPlayers.containsKey(player)) {
            invisPlayers.remove(player);
            PlayerInventory inventory = player.getInventory();
            setFakeArmor(player,Arrays.asList(inventory.getItemInMainHand(),inventory.getItemInOffHand(),inventory.getBoots(),inventory.getLeggings(),inventory.getChestplate(),inventory.getHelmet()));
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }


    public static void setFakeArmor(Player player,List<ItemStack> items){

        final List<Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>> equipmentList = new ArrayList<>();

        equipmentList.add(new Pair<>(EnumItemSlot.a, CraftItemStack.asNMSCopy(items.get(0))));
        equipmentList.add(new Pair<>(EnumItemSlot.b, CraftItemStack.asNMSCopy(items.get(1))));
        equipmentList.add(new Pair<>(EnumItemSlot.c, CraftItemStack.asNMSCopy(items.get(2))));
        equipmentList.add(new Pair<>(EnumItemSlot.d, CraftItemStack.asNMSCopy(items.get(3))));
        equipmentList.add(new Pair<>(EnumItemSlot.e, CraftItemStack.asNMSCopy(items.get(4))));
        equipmentList.add(new Pair<>(EnumItemSlot.f, CraftItemStack.asNMSCopy(items.get(5))));

        PacketPlayOutEntityEquipment handPacket = new PacketPlayOutEntityEquipment(player.getEntityId(), equipmentList);
        for(Entity ent : player.getNearbyEntities(50, 50, 50)) {
            if(!(ent instanceof Player) || ent == player) continue;
            Player reciever = (Player) ent;
            ((CraftPlayer)reciever).getHandle().b.sendPacket(handPacket);
        }
    }
}
