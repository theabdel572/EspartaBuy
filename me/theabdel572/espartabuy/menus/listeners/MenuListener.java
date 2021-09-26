package me.theabdel572.espartabuy.menus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import me.theabdel572.espartabuy.EspartaBuy;
import me.theabdel572.espartabuy.Utils;
import me.theabdel572.espartabuy.menus.Menu;
import me.theabdel572.espartabuy.mysql.MySQLData;

public class MenuListener implements Listener{
	private final EspartaBuy plugin;
	public MenuListener(EspartaBuy plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClickMenu(InventoryClickEvent e) {
		FileConfiguration config = plugin.getConfig();
		
		String title = e.getClickedInventory().getTitle();
		int slot = e.getSlot();
		Player player = (Player) e.getWhoClicked();
		
		if(title.equals(Utils.colorize(config.getString("Menu.Name")))
				|| title.equals(Utils.colorize(config.getString("RanksMenu.Name"))) 
				|| title.equals(Utils.colorize(config.getString("RainbowArmorsMenu.Name"))) 
				|| title.equals(Utils.colorize(config.getString("TagsMenu.Name")))) {
			
			e.setCancelled(true);
		}
		
		if(title.equals(Utils.colorize(config.getString("Menu.Name"))) && e.getInventory().getType() == InventoryType.HOPPER) {
			Menu menus = new Menu(plugin);
			switch(slot) {
			case 0:
				menus.openRanksMenu(player);
				break;
			case 2:
				menus.openRainbowArmorsMenu(player);
				break;
			case 4:
				menus.openTagsMenu(player);
				break;
			default:
				break;
			}
		}
		
		if(title.equals(Utils.colorize(config.getString("RanksMenu.Name")))) {
			String path = "RanksMenuItems."+slot;
			int cost = config.getInt(path+".Cost");
			
			if(config.contains(path+".Material") 
					&& MySQLData.getCredits(plugin.getMySQL(), player.getUniqueId()) >= cost) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), config.getString(path+".Command").replaceAll("%player%", player.getName()));
			}
		}else if(title.equals(Utils.colorize(config.getString("RainbowArmorsMenu.Name")))) {
			String path = "RainbowArmorsMenuItems."+slot;
			int cost = config.getInt(path+".Cost");
			
			if(config.contains(path+".Material") 
					&& MySQLData.getCredits(plugin.getMySQL(), player.getUniqueId()) >= cost) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), config.getString(path+".Command").replaceAll("%player%", player.getName()));
			}
		}else if(title.equals(Utils.colorize(config.getString("TagsMenu.Name")))) {
			String path = "TagsMenuItems."+slot;
			int cost = config.getInt(path+".Cost");
			
			if(config.contains(path+".Material") 
					&& MySQLData.getCredits(plugin.getMySQL(), player.getUniqueId()) >= cost) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), config.getString(path+".Command").replaceAll("%player%", player.getName()));
			}
		}
	}
}