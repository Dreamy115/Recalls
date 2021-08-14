package me.Dreamy.Recalls.Commands;

import me.Dreamy.Recalls.Main;
import me.Dreamy.Recalls.Methods.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdDelwarp implements CommandExecutor {

    private Main plugin;

    public cmdDelwarp(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rc.warp.manage")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cNot enough permissions."));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cPlease provide a warp name."));
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cOnly Players can execute this command."));
            return false;
        }

        try {
            WarpManager.Waystone warp = new WarpManager.Waystone(plugin.waystones, args[0]);
            warp.delete();
        } catch (Exception ignored) {}

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&aDone!"));

        return true;
    }
}
