package me.Dreamy.Recalls.Commands;

import me.Dreamy.Recalls.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdReload implements CommandExecutor {

    private final Main plugin;

    public cmdReload(Main _plugin) {
        plugin = _plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("rc.reload")) {
            plugin.config.reloadConfig();
            plugin.playerData.reloadConfig();
            plugin.waystones.reloadConfig();

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&aReloaded!"));
            return true;
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cNot enough permissions!"));

        return false;
    }
}
