package me.Dreamy.Recalls.TabCompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class tabPlayerList implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> list = new ArrayList<>();
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			list.add(p.getName());
		}
		return list;
	}
}
