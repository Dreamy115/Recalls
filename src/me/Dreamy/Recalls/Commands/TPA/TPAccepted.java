package me.Dreamy.Recalls.Commands.TPA;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dreamy.Recalls.Main;
import me.Dreamy.Recalls.Methods.Transport;
import me.Dreamy.Recalls.Methods.TpaRequests;
import net.md_5.bungee.api.ChatColor;

public class TPAccepted implements CommandExecutor {

	private Main plugin;

	public TPAccepted(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		plugin.config.getConfig().options().copyDefaults(true);
		String prefix = ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix"));
		
		Player p = (Player) sender;

		if (!p.hasPermission("rc.tpa.accept")) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou cannot accept TPA requests."));
			return false;
		}
		
		if(!TpaRequests.request.containsKey(p.getName())) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cThere's no pending TPA requests."));
			return false;
		} else {
			Player p2 = Bukkit.getPlayer(TpaRequests.request.get(p.getName()));
			
			TpaRequests.removeRequest(p.getName());
			
			if(p2 != null) {
				p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aTeleporting player &r" + p2.getDisplayName() + " &a to you."));

				new Transport(p2, p.getLocation(), plugin, true);
				return true;
			} else {
				p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&3Accepted, but player has left."));
				return false;
			}
		}
	}
}
