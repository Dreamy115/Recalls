package me.Dreamy.Recalls.Commands;

import me.Dreamy.Recalls.Main;
import me.Dreamy.Recalls.Methods.BedManager;
import me.Dreamy.Recalls.Methods.Transport;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdBed implements CommandExecutor {

    private Main plugin;

    public cmdBed(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can do this");
            return false;
        }

        if (Transport.commencingTeleports.containsKey(((Player) sender).getUniqueId())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cAlready Teleporting!"));
            return false;
        }

        if (!sender.hasPermission("rc.bed.use")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cNot enough permissions."));
            return false;
        }

        String bedName;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cPlease provide a bed name"));
            return false;
        }
        bedName = args[0];

        try {
            BedManager.PlayerBed bed = new BedManager.PlayerBed(plugin.playerData, ((Player) sender).getUniqueId(), bedName);
            if (!bed.checkIntegrity()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cBed deleted or obstructed."));
                return false;
            }

            new Transport((Player) sender, bed.getLocation(), plugin, false);
        } catch (NullPointerException exception) {
            if (exception.getMessage().equals("Bed not found")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cBed not found."));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cErrored"));
                plugin.getLogger().severe(exception.getMessage());
            }
            return false;
        }

        return true;
    }
}
