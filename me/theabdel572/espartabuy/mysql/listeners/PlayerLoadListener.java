package me.theabdel572.espartabuy.mysql.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.theabdel572.espartabuy.EspartaBuy;
import me.theabdel572.espartabuy.mysql.MySQLData;

public class PlayerLoadListener implements Listener{
	private final EspartaBuy plugin;
	public PlayerLoadListener(EspartaBuy plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if(!MySQLData.hasPlayer(plugin.getMySQL(), player.getUniqueId())) {
			MySQLData.createPlayer(plugin.getMySQL(), player.getName(), player.getUniqueId());
		}
	}
}