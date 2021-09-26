package me.theabdel572.espartabuy.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.theabdel572.espartabuy.EspartaBuy;
import me.theabdel572.espartabuy.ItemBuilder;
import me.theabdel572.espartabuy.Utils;

public class Menu{
	private final EspartaBuy plugin;
	public Menu(EspartaBuy plugin) {
		this.plugin = plugin;
	}
	
	public void openMenu(Player player) {
		FileConfiguration config = plugin.getConfig();
		
		Inventory menu = Bukkit.createInventory(null, InventoryType.HOPPER, Utils.colorize(config.getString("Menu.Name")));
		
		ItemBuilder ranks = new ItemBuilder(Material.DIAMOND_SWORD, "Menu.RanksName", plugin), 
				rainbowArmors = new ItemBuilder(Material.GOLD_CHESTPLATE, "Menu.RainbowArmorsName", plugin), 
				tags = new ItemBuilder(Material.NAME_TAG, "Menu.TagsName", plugin);
		
		ranks.addToInventory(menu, 0);
		rainbowArmors.addToInventory(menu, 2);
		tags.addToInventory(menu, 4);
		player.openInventory(menu);
	}
	
	public void openRanksMenu(Player player) {
		FileConfiguration config = plugin.getConfig();
		
		Inventory menu = Bukkit.createInventory(null, config.getInt("RanksMenu.Slots"), 
				Utils.colorize(config.getString("RanksMenu.Name")));
		
		for(String key : config.getConfigurationSection("RanksMenuItems").getKeys(false)) {
			String path = "RanksMenuItems."+key;
			
			ItemStack item = new ItemStack(Material.valueOf(config.getString(path+".Material")));
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(Utils.colorize(config.getString(path+".Name")));
			
			if(config.contains(path+".Lore")) {
				meta.setLore(Utils.colorize(config.getStringList(path+".Lore")));
			}
			
			item.setItemMeta(meta);
			
			menu.setItem(Integer.valueOf(key), item);
		}
		player.openInventory(menu);
	}
	
	public void openRainbowArmorsMenu(Player player) {
		FileConfiguration config = plugin.getConfig();
		
		Inventory menu = Bukkit.createInventory(null, config.getInt("RainbowArmorsMenu.Slots"), 
				Utils.colorize(config.getString("RainbowArmorsMenu.Name")));
		
		for(String key : config.getConfigurationSection("RainbowArmorsMenuItems").getKeys(false)) {
			String path = "RainbowArmorsMenuItems."+key;
			
			ItemStack item = new ItemStack(Material.valueOf(config.getString(path+".Material")));
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(Utils.colorize(config.getString(path+".Name")));
			
			if(config.contains(path+".Lore")) {
				meta.setLore(Utils.colorize(config.getStringList(path+".Lore")));
			}
			
			item.setItemMeta(meta);
			
			menu.setItem(Integer.valueOf(key), item);
		}
		player.openInventory(menu);
	}
	
	public void openTagsMenu(Player player) {
		FileConfiguration config = plugin.getConfig();
		
		Inventory menu = Bukkit.createInventory(null, config.getInt("TagsMenu.Slots"), 
				Utils.colorize(config.getString("TagsMenu.Name")));
		
		for(String key : config.getConfigurationSection("TagsMenuItems").getKeys(false)) {
			String path = "TagsMenuItems."+key;
			
			ItemStack item = new ItemStack(Material.valueOf(config.getString(path+".Material")));
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(Utils.colorize(config.getString(path+".Name")));
			
			if(config.contains(path+".Lore")) {
				meta.setLore(Utils.colorize(config.getStringList(path+".Lore")));
			}
			
			item.setItemMeta(meta);
			
			menu.setItem(Integer.valueOf(key), item);
		}
		player.openInventory(menu);
	}
}
