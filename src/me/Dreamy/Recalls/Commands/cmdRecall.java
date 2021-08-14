package me.Dreamy.Recalls.Commands;

import me.Dreamy.Recalls.Main;
import me.Dreamy.Recalls.Methods.Transport;
import me.Dreamy.Recalls.Methods.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdRecall implements CommandExecutor {

    private final Main plugin;

    public cmdRecall(Main _plugin) {
        plugin = _plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (((Player) sender).getLocation().getWorld().getEnvironment().equals(World.Environment.NETHER)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&4&kI can't help you..."));
            return false;
        }

        if (Transport.commencingTeleports.containsKey(((Player) sender).getUniqueId())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cAlready Teleporting!"));
            return false;
        }

        String warpName = args[0];
        if (warpName == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cPlease provide a warp name."));
            return false;
        }

        if (!(sender.hasPermission("rc.warp.use.*") || sender.hasPermission("rc.warp.use." + warpName))) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cYou don't have permissions to recall to &9" + warpName + "&c."));
            return false;
        }

        WarpManager.Waystone warp;
        try {
            warp = new WarpManager.Waystone(plugin.waystones, warpName);
        } catch (NullPointerException exception) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cCouldn't find warp &9" + warpName + "&c."));
            return false;
        }

        new Transport((Player) sender, warp.getLocation(), plugin, false);

        return true;
    }
}
