package me.theabdel572.espartabuy.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLData {
	public static boolean hasPlayer(Connection con, UUID uuid) {
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Player WHERE (UUID=?)");
			statement.setString(1, uuid.toString());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
		}catch(SQLException ex) {
			
		}
		return false;
	}
	
	public static void createPlayer(Connection con, String name, UUID uuid) {
		try {
			if(!hasPlayer(con, uuid)) {
				PreparedStatement statement = con.prepareStatement("INSERT INTO Player VALUE (?,?,?)");
				statement.setString(1, uuid.toString());
				statement.setString(2, name);
				statement.setInt(3, 0);
				statement.executeUpdate();
			}
		}catch(SQLException ex) {
			
		}
	}
	
	public static int getCredits(Connection con, UUID uuid) {
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Player WHERE (UUID=?)");
			statement.setString(1, uuid.toString());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				int credits = result.getInt("Créditos");
				return credits;
			}
		}catch(SQLException ex) {
			
		}
		return 0;
	}
	public static void setCredits(Connection con, UUID uuid, int credits) {
		try {
			PreparedStatement statement = con.prepareStatement("UPDATE Player SET Créditos=? WHERE (UUID=?)");
			statement.setInt(1, credits);
			statement.setString(2, uuid.toString());
			statement.executeUpdate();
		}catch(SQLException ex) {
			
		}
	}
}