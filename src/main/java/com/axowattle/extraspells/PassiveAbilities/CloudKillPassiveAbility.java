package com.axowattle.extraspells.PassiveAbilities;

import com.axowattle.extraspells.ExtraSpells;
import com.axowattle.extraspells.Projectile.ProjectileHandler;
import com.axowattle.extraspells.Projectiles.CloudKillProjectile;
import com.axowattle.extraspells.Spells.Druid.CloudKillSpell;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.particles.ParticleParam;
import net.minecraft.core.particles.ParticleParamItem;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.Particles;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.animal.EntitySquid;
import net.minecraft.world.entity.monster.EntityEnderman;
import net.minecraft.world.item.ItemStack;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloudKillPassiveAbility extends PassiveAbility{
    public CloudKillPassiveAbility() {
        super("CloudKill", "Makes you a cloud that kills.", 8);
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (CloudKillSpell.isCloud(event.getPlayer())) {
            ProjectileHandler.createProjectile(new CloudKillProjectile(event.getPlayer()));
        }
    }

    @Override
    public void onTick(Player player) {
        if (CloudKillSpell.isCloud(player)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10,10));

            Location loc = player.getLocation();
            PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(Particles.Y, false, loc.getX(), loc.getY(), loc.getZ(), 5, 5, 5, 0, 300);

            PacketPlayOutEntityEquipment armorPacket = setFakeArmor(player, Arrays.asList(new org.bukkit.inventory.ItemStack(Material.AIR),new org.bukkit.inventory.ItemStack(Material.AIR),new org.bukkit.inventory.ItemStack(Material.AIR),new org.bukkit.inventory.ItemStack(Material.AIR),new org.bukkit.inventory.ItemStack(Material.AIR),new org.bukkit.inventory.ItemStack(Material.AIR)));

            for (LivingEntity entity : getAllEntities(player.getLocation(),5)){
                if (entity.getUniqueId() != player.getUniqueId())
                    entity.damage(4,player);
            }

            for (Player p : Bukkit.getOnlinePlayers()){
                if (p.getUniqueId() != player.getUniqueId()){
                    PlayerConnection connection = ((CraftPlayer)p).getHandle().b;
                    connection.sendPacket(particles);
                    connection.sendPacket(armorPacket);
                }
            }
        }
    }

    @Override
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (CloudKillSpell.isCloud(event.getPlayer())) {
            Bukkit.getScheduler().runTaskAsynchronously(ExtraSpells.getInstance(), new Runnable() {
                @Override
                public void run() {
                    CloudKillSpell.resetInventory(event.getPlayer());
                }
            });

            event.setCancelled(true);
        }
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (CloudKillSpell.isCloud(event.getPlayer()))
            CloudKillSpell.resetInventory(event.getPlayer());
    }

    @Override
    public void onDisable(Player player) {
        if (CloudKillSpell.isCloud(player))
            CloudKillSpell.resetInventory(player);
    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (CloudKillSpell.isCloud(event.getEntity())) {
            event.getDrops().clear();
            event.getDrops().addAll(CloudKillSpell.getCloudPlayers().get(event.getEntity()).getItems());
            CloudKillSpell.resetInventory(event.getEntity());
        }
    }

    public final List<LivingEntity> getAllEntities(Location location, double radius){
        List<LivingEntity> entities = new ArrayList<>();
        for(Entity entity : location.getWorld().getNearbyEntities(location,radius,radius,radius)){
            if (entity instanceof LivingEntity & location.distance(entity.getLocation()) < radius){
                entities.add((LivingEntity) entity);
            }
        }
        return entities;
    }

    public static PacketPlayOutEntityEquipment setFakeArmor(Player player,List<org.bukkit.inventory.ItemStack> items){

        final List<Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>> equipmentList = new ArrayList<>();

        equipmentList.add(new Pair<>(EnumItemSlot.a, CraftItemStack.asNMSCopy(items.get(0))));
        equipmentList.add(new Pair<>(EnumItemSlot.b, CraftItemStack.asNMSCopy(items.get(1))));
        equipmentList.add(new Pair<>(EnumItemSlot.c, CraftItemStack.asNMSCopy(items.get(2))));
        equipmentList.add(new Pair<>(EnumItemSlot.d, CraftItemStack.asNMSCopy(items.get(3))));
        equipmentList.add(new Pair<>(EnumItemSlot.e, CraftItemStack.asNMSCopy(items.get(4))));
        equipmentList.add(new Pair<>(EnumItemSlot.f, CraftItemStack.asNMSCopy(items.get(5))));

        PacketPlayOutEntityEquipment handPacket = new PacketPlayOutEntityEquipment(player.getEntityId(), equipmentList);
        return handPacket;
    }
}
