package me.Dreamy.Recalls.Commands.TPA;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dreamy.Recalls.Main;
import me.Dreamy.Recalls.Methods.TpaRequests;
import net.md_5.bungee.api.ChatColor;

public class TPDenied implements CommandExecutor {

	private Main plugin;

	public TPDenied(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("message-prefix"));
		
		Player p = (Player) sender;
		
		if(!TpaRequests.request.containsKey(p.getName())) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cThere's no pending TPA requests."));
			return false;
		} else {
			Player p2 = Bukkit.getPlayer(TpaRequests.request.get(p.getName()));
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&r" + p2.getDisplayName() + "'s &bTPA Request denied."));
			p2.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&r" + p.getDisplayName() + " &cdenied your TPA request."));
			TpaRequests.removeRequest(p.getName());
			return true;
		}
	}
}
