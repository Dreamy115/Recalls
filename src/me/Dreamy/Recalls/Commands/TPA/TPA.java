package me.Dreamy.Recalls.Commands.TPA;

import me.Dreamy.Recalls.Methods.Transport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dreamy.Recalls.Main;
import me.Dreamy.Recalls.Methods.Transport;
import me.Dreamy.Recalls.Methods.TpaRequests;

public class TPA implements CommandExecutor {

	private Main plugin;

	public TPA(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		plugin.config.getConfig().options().copyDefaults(true);
		String prefix = ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix"));

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Transport.commencingTeleports.containsKey(player.getUniqueId())) {
				player.sendMessage(prefix + ChatColor.RED + "Another teleport is taking place!");
				return false;
			}

			if (player.getLocation().getWorld().getEnvironment().equals(World.Environment.NETHER)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&4&kI can't help you..."));
				return false;
			}

			if (args.length == 0) {
				player.sendMessage(prefix + ChatColor.RED + "Please provide a player's name");
				return false;
			} else {
				if (player.hasPermission("rc.tpa.send")) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						player.sendMessage(prefix + ChatColor.RED + "Cannot find player " + ChatColor.DARK_RED + args[0]);
						return false;
					} else if(target == player) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3It's you!"));
						return false;
					} else {
						if(!(
								player.hasPermission("st.recalls.world.".concat(target.getLocation().getWorld().getName())) ||
								player.hasPermission("st.recalls.world.*")	
							)) {		
								player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cYou cannot TPA to world &8" + target.getLocation().getWorld().getName()));
								return false;
							}
						
						TpaRequests.sendRequest(player, target);
						
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							
							@Override
							public void run() {
								TpaRequests.removeRequest(target.getName());
							}
							
						}, 20 * plugin.config.getConfig().getInt("teleport.tpa-timeout"));
						
						return true;
					}
				} else {
					player.sendMessage(prefix + ChatColor.RED + "You don't have permission to teleport to players.");
					return false;
				}
			}
		} else {
			sender.sendMessage(prefix + ChatColor.RED + "Only players can use this command!");
			return false;
		}
	}
	
}
