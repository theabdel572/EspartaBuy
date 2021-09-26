package me.theabdel572.espartabuy;

import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.theabdel572.espartabuy.commands.EconomyCommand;
import me.theabdel572.espartabuy.menus.commands.MenuCommand;
import me.theabdel572.espartabuy.menus.listeners.MenuListener;
import me.theabdel572.espartabuy.mysql.MySQLConnection;
import me.theabdel572.espartabuy.mysql.listeners.PlayerLoadListener;

public class EspartaBuy extends JavaPlugin{
	private final PluginDescriptionFile plfile = getDescription();
	private final String version = plfile.getVersion();
	private final String name = Utils.colorize("&6[&bEspartaBuy&6]");
	private FileCreator config;
	
	private MySQLConnection connection;

	public void onEnable() {
		this.config = new FileCreator(this, "config.yml");
		registerCommands();
		registerEvents();
		this.connection = new MySQLConnection(config.getString("MySQL.Host"), 
				config.getInt("MySQL."), "creditos", "odisean", "odiseannetwork");
	}

	public String getVersion() {
		return version;
	}
	public String getPluginName() {
		return name;
	}
	public FileCreator getConfig() {
		return this.config;
	}
	
	public void registerCommands() {
		this.getCommand("ebuy").setExecutor(new EconomyCommand(this));
		this.getCommand("buy").setExecutor(new MenuCommand(this));
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new PlayerLoadListener(this), this);
		pm.registerEvents(new MenuListener(this), this);
	}
	
	public Connection getMySQL() {
		return this.connection.getConnection();
	}
}
