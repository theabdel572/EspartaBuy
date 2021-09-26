package me.theabdel572.espartabuy;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	private ItemStack item;
	
	public ItemBuilder(Material material, String name, EspartaBuy plugin) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.colorize(plugin.getConfig().getString(name)));
		item.setItemMeta(meta);
		this.item = item;
	}
	
	public void addToInventory(Inventory inv, int slot) {
		inv.setItem(slot, item);
	}
}
