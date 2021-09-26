package me.theabdel572.espartabuy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {
	public static String colorize(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static void sendConsoleMessage(String text) {
		Bukkit.getConsoleSender().sendMessage(Utils.colorize(text));
	}

	public static List<String> colorize(List<String> stringList) {
		List<String> list = new ArrayList<>();
		for(int i=0; i<stringList.size(); i++) {
			list.add(i, Utils.colorize(stringList.get(i)));
		}
		return list;
	}
}
