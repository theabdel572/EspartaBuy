package me.theabdel572.espartabuy.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.theabdel572.espartabuy.EspartaBuy;
import me.theabdel572.espartabuy.Utils;
import me.theabdel572.espartabuy.mysql.MySQLData;

public class EconomyCommand implements CommandExecutor{
	private final EspartaBuy plugin;
	public EconomyCommand(EspartaBuy plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender.hasPermission("ebuy.use")) {
			if(args.length >= 3 || args[0].equalsIgnoreCase("reload")) {
				if(args[0].equalsIgnoreCase("reload")) {
					plugin.getConfig().reload();
					sender.sendMessage(plugin.getPluginName() + Utils.colorize("&a The config has been reloaded."));
					return true;
				}
				Player player = Bukkit.getPlayer(args[1]);
				
				switch(args[0].toLowerCase()) {
				
				case "give":
					changeCredits(sender, player, args, true);
					return true;
					
				case "take":
					changeCredits(sender, player, args, false);
					return true;
					
				default: sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &cUse: /ebuy [give/take] <player> <credits>")); return false;
				}
			}else {
				sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &cUse: /ebuy [give/take] <player> <credits>"));
				return false;
			}
		}else {
			sender.sendMessage(Utils.colorize(plugin.getConfig().getString("Messages.NoPermission")));
			return false;
		}
	}
	
	private void changeCredits(CommandSender sender, Player player, String[] args, boolean giving) {
		if(player != null) {
			try {
				int credits = Integer.valueOf(args[2]);
				
				if(credits > 0) {
					
					int playerCredits = MySQLData.getCredits(plugin.getMySQL(), player.getUniqueId());
					
					if(giving) {
						MySQLData.setCredits(plugin.getMySQL(), player.getUniqueId(), playerCredits + credits);
						sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &2You have given &7"+player.getName()+" &a"+credits+" &2credits."));
						return;
						
					}else {
						if((playerCredits - credits) >= 0) {
							MySQLData.setCredits(plugin.getMySQL(), player.getUniqueId(), playerCredits - credits);
							sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &2You have taken &c"+credits+" &2credits from &7"+player.getName()));
							return;
						}else {
							MySQLData.setCredits(plugin.getMySQL(), player.getUniqueId(), 0);
							sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &2You have taken all the credits from &7"+player.getName()));
							return;
						}
					}
					
				}else {
					sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &cCredits to give/take must be > 0."));
				}
			}catch(NumberFormatException ex) {
				sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &cUse: /ebuy [give/take] <player> <credits>"));
			}
			
		}else {
			@SuppressWarnings("deprecation")
			UUID uuid = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
			
			if(MySQLData.hasPlayer(plugin.getMySQL(), uuid)) {
				try {
					int credits = Integer.valueOf(args[2]);
					
					if(credits > 0) {
						
						int playerCredits = MySQLData.getCredits(plugin.getMySQL(), uuid);
						
						if(giving) {
							MySQLData.setCredits(plugin.getMySQL(), uuid, playerCredits + credits);
							sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &2You have given &7"+Bukkit.getPlayer(uuid).getName()+" &a"+credits+" &2credits."));
							return;
							
						}else {
							if((playerCredits - credits) >= 0) {
								MySQLData.setCredits(plugin.getMySQL(), uuid, playerCredits - credits);
								sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &2You have taken &c"+credits+" &2credits from &7"+Bukkit.getPlayer(uuid).getName()));
								return;
							}else {
								MySQLData.setCredits(plugin.getMySQL(), uuid, 0);
								sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &2You have taken all the credits from &7"+Bukkit.getPlayer(uuid).getName()));
								return;
							}
						}
					}else {
						sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &cCredits to give must be > 0"));
					}
				}catch(NumberFormatException ex) {
					sender.sendMessage(Utils.colorize(plugin.getPluginName()+" &cUse: /ebuy [give/take] <player> <credits>"));
				}
			}else {
				sender.sendMessage(Utils.colorize(plugin.getPluginName() + "&cThat player has never joined the server."));
			}
		}
	}
}