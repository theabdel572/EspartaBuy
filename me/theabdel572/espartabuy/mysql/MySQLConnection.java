package me.theabdel572.espartabuy.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import me.theabdel572.espartabuy.Utils;

public class MySQLConnection {
	private Connection connection;
	private String host;
	private int port;
	private String database;
	private String user;
	private String pass;
	
	public MySQLConnection(String host, int port, String database, String user, String pass) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.pass = pass;
		try{
			synchronized(this) {
				if(connection != null && !connection.isClosed()) {
					Utils.sendConsoleMessage("&6[&bPrueba&6] &4Error connecting with MySQL.");
					Bukkit.getConsoleSender().sendMessage("ERROR CONNECTING WITH MYSQL");
					return;
				}
				Bukkit.getConsoleSender().sendMessage("1");
				
				Class.forName("com.mysql.jdbc.Driver");
				
				Bukkit.getConsoleSender().sendMessage("2");
				
				this.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database, 
						this.user, this.pass);
				
				Bukkit.getConsoleSender().sendMessage("3");
				
				Utils.sendConsoleMessage("&6[&bPrueba&6] &2Connected successfully to MySQL database.");
			}
		}catch(SQLException ex) {
			
		}catch(ClassNotFoundException ex) {
			
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
