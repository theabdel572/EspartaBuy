package me.theabdel572.espartabuy.menus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.theabdel572.espartabuy.EspartaBuy;
import me.theabdel572.espartabuy.Utils;
import me.theabdel572.espartabuy.menus.Menu;

public class MenuCommand implements CommandExecutor{
	private final EspartaBuy plugin;
	public MenuCommand(EspartaBuy plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(!(sender instanceof Player)) {
			Utils.sendConsoleMessage(plugin.getPluginName()+" &cThis command only can be executed by players.");
			return false;
		}else {
			Player player = (Player) sender;
			new Menu(plugin).openMenu(player);
			return true;
		}
	}
}
