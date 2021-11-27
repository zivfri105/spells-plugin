package com.axowattle.extraspells.Spells;

import com.axowattle.extraspells.ExtraSpells;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Candle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.*;

public class WandHandler {
    private static Vector[] candles = new Vector[]{new Vector(2,0,0),new Vector(2,0,1),new Vector(2,0,2),new Vector(1,0,2),
            new Vector(0,0,2),new Vector(-1,0,2),new Vector(-2,0,2),new Vector(-2,0,1),new Vector(-2,0,0),
            new Vector(-2,0,-1),new Vector(-2,0,-2),new Vector(-1,0,-2),new Vector(0,0,-2),new Vector(1,0,-2),
            new Vector(2,0,-2),new Vector(2,0,-1)};
    private static List<Material> candleMaterials = Arrays.asList(Material.CANDLE,Material.WHITE_CANDLE,Material.ORANGE_CANDLE,Material.MAGENTA_CANDLE,
            Material.LIGHT_BLUE_CANDLE,Material.YELLOW_CANDLE,Material.LIME_CANDLE,Material.PINK_CANDLE, Material.GRAY_CANDLE,Material.LIGHT_GRAY_CANDLE,
            Material.CYAN_CANDLE,Material.PURPLE_CANDLE, Material.BLUE_CANDLE,Material.BROWN_CANDLE,Material.GREEN_CANDLE,Material.RED_CANDLE,Material.BLACK_CANDLE);
    private static Set<Material> notViable = new HashSet<Material>(Arrays.asList(Material.AIR,Material.CAVE_AIR,Material.VOID_AIR));

    private static List<Integer> emptySlots = Arrays.asList(13,14,15,22,23,24,31,32,33,40,41,42);

    private static final List<WandRecipe> recipes = Arrays.asList(
            new WandRecipe(Arrays.asList(new ItemStack(Material.AMETHYST_SHARD),new ItemStack(Material.STICK)), getWandDefault()),
            new WandRecipe(Arrays.asList(getWandDefault(),getCraftingItem(4)),getWandNormal()),
            new WandRecipe(Arrays.asList(getSuperFlesh(5),new ItemStack(Material.LAPIS_BLOCK,64)), getManaArtifact(1)));


    public static WandRecipe getViableRecipe(List<ItemStack> items){
        if(items.size() == 0)
            return null;
        for (WandRecipe recipe : recipes){
            boolean isRecipe = true;
            for (ItemStack item : items){
                if (!recipe.containsItem(item))
                    isRecipe = false;
            }
            if (isRecipe){
                return recipe;
            }
        }
        return null;
    }

    public static boolean isSlotEmpty(int slot){
        return emptySlots.contains(slot);
    }

    public static boolean isCandles(Location location){
        for (Vector candle : candles){
            Location newLocation = location.clone().add(candle);
            Candle blockCandle = (Candle) newLocation.getBlock().getBlockData();
            if (!candleMaterials.contains(newLocation.getBlock().getType()) | !blockCandle.isLit()){
                return false;
            }
        }
        return true;
    }

    public static void openWandCraftMenu(Player player) {

        player.closeInventory();

        PlayerData playerData = PlayerData.getPlayerData(player);
        playerData.openInventory(3);

        Inventory inventory = Bukkit.createInventory(player,54,"Wand Crafter");

        inventory.setItem(10,SpellHandler.createItem(Material.AMETHYST_SHARD,"Combine materials"));

        for (int i = 0;i<inventory.getSize();i++){
            if (inventory.getItem(i) == null & !emptySlots.contains(i)){
                inventory.setItem(i,SpellHandler.createItem(Material.GRAY_STAINED_GLASS_PANE," "));
            }
        }

        player.openInventory(inventory);

    }

    public static List<ItemStack> getAllItems(Inventory inventory){
        List<ItemStack> items = new ArrayList<>();
        for (int slot : emptySlots){
            if (inventory.getItem(slot) != null) items.add(inventory.getItem(slot));
        }
        return items;
    }

    public static void craftItem(Inventory clickedInventory, Player player){
        Block enchantingTable = player.getTargetBlock(notViable,10);

        if (enchantingTable.getType() != Material.ENCHANTING_TABLE)
            return;

        WandRecipe wandRecipe = getViableRecipe(getAllItems(clickedInventory));
        if (wandRecipe == null){
            player.sendMessage(ChatColor.RED + "No recipe available with these items.");
        }else {
            Item item = (Item) enchantingTable.getWorld().spawnEntity(enchantingTable.getLocation().add(0,1,0),EntityType.DROPPED_ITEM);
            item.setItemStack(wandRecipe.result);
        }
    }

    public static void particleBeamByTime(Location start,Location end,float timeTakes,Particle particle){

        Vector dir = end.toVector().subtract(start.toVector());

        float magnitude = (float) Math.sqrt((dir.getX()*dir.getX())+(dir.getY()*dir.getY())+(dir.getZ()*dir.getZ()));

        float moveSpeed = magnitude / timeTakes;


        Vector vecOffset = dir.clone().normalize().multiply(moveSpeed / 20);

        Location location = start.clone();

        int tick = Bukkit.getScheduler().scheduleSyncRepeatingTask(ExtraSpells.getInstance(), new Runnable() {
            @Override
            public void run() {
                location.add(vecOffset);
                start.getWorld().spawnParticle(particle,location,0);
            }
        },1,1);

        int timer = Bukkit.getScheduler().scheduleSyncDelayedTask(ExtraSpells.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().cancelTask(tick);
            }
        },(long) timeTakes*20);

    }

    public static ItemStack getWandDefault(){
        ItemStack itemStack = new ItemStack(Material.WOODEN_SWORD,1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ENCHANTS);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier("generic.attack_damage",-4, AttributeModifier.Operation.ADD_NUMBER));
        itemMeta.setDisplayName(ChatColor.AQUA + "Default Wand");
        itemMeta.addEnchant(Enchantment.DURABILITY,1,false);
        itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Use this wand to cast spells."));
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        data.set(SpellHandler.getWandIdKey(), PersistentDataType.INTEGER,1);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack getWandNormal(){
        ItemStack itemStack = new ItemStack(Material.STONE_SWORD,1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ENCHANTS);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier("generic.attack_damage",-8, AttributeModifier.Operation.ADD_NUMBER));
        itemMeta.setDisplayName(ChatColor.AQUA + "Normal Wand");
        itemMeta.addEnchant(Enchantment.DURABILITY,1,false);
        itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Use this wand to cast spells."));
        itemMeta.setUnbreakable(true);
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        data.set(SpellHandler.getManaMaxKey(), PersistentDataType.INTEGER,50);
        data.set(SpellHandler.getWandIdKey(), PersistentDataType.INTEGER,2);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getCraftingItem(int amount){
        ItemStack itemStack = new ItemStack(Material.RAW_GOLD,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ENCHANTS);
        itemMeta.setDisplayName(ChatColor.AQUA + "Magic Shard");
        itemMeta.addEnchant(Enchantment.DURABILITY,1,false);
        itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Can be used to craft better wands."));
        itemMeta.setUnbreakable(true);
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        data.set(SpellHandler.getManaMaxKey(), PersistentDataType.INTEGER,10);
        data.set(SpellHandler.getWandIdKey(), PersistentDataType.INTEGER,-1);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getSuperFlesh(int amount){
        ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ENCHANTS);
        itemMeta.setDisplayName(ChatColor.AQUA + "Super Flesh");
        itemMeta.addEnchant(Enchantment.DURABILITY,1,false);
        itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Can be used to craft a mana artifact."));
        itemMeta.setUnbreakable(true);
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        data.set(SpellHandler.getManaMaxKey(), PersistentDataType.INTEGER,10);
        data.set(SpellHandler.getWandIdKey(), PersistentDataType.INTEGER,-2);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getManaArtifact(int amount){
        ItemStack itemStack = new ItemStack(Material.LAPIS_LAZULI,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ENCHANTS);
        itemMeta.setDisplayName(ChatColor.BLUE + "Mana Artifact");
        itemMeta.addEnchant(Enchantment.DURABILITY,1,false);
        itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Can be used to craft a better wand."));
        itemMeta.setUnbreakable(true);
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        data.set(SpellHandler.getManaMaxKey(), PersistentDataType.INTEGER,200);
        data.set(SpellHandler.getWandIdKey(), PersistentDataType.INTEGER,-3);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static class WandRecipe{
        private List<ItemStack> materialsNeeded;
        private ItemStack result;

        public WandRecipe(List<ItemStack> materialsNeeded,ItemStack result){
            this.materialsNeeded = materialsNeeded;
            this.result = result;
        }

        public ItemStack getResult() {
            return result;
        }

        public List<ItemStack> getMaterialsNeeded() {
            return materialsNeeded;
        }

        public boolean containsItem(ItemStack itemStack){
            for (ItemStack item : materialsNeeded){
                if (isSimilar(item,itemStack))
                    return true;
            }
            return false;
        }
    }

    private static boolean isSimilar(ItemStack item1,  ItemStack item2){
        return item1.getAmount() == item2.getAmount() & item1.getType() == item2.getType() & item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName());
    }

    public static float getDistance(Vector start,Vector end){
        Vector dir = end.subtract(start);

        float magnitude = (float) Math.sqrt((dir.getX()*dir.getX())+(dir.getY()*dir.getY())+(dir.getZ()*dir.getZ()));
        return Math.abs(magnitude);
    }
}
