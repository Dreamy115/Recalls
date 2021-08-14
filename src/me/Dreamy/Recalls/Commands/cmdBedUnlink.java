package me.Dreamy.Recalls.Commands;

import me.Dreamy.Recalls.Main;
import me.Dreamy.Recalls.Methods.BedManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdBedUnlink implements CommandExecutor {

    private Main plugin;

    public cmdBedUnlink(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can do this");
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
            bed.delete();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&aBed Unlinked!"));
        } catch (Exception ignored) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&7No such bed."));
            return false;
        }

        return true;
    }
}
