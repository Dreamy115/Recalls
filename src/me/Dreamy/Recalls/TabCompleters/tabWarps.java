package me.Dreamy.Recalls.TabCompleters;

import me.Dreamy.Recalls.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class tabWarps implements TabCompleter {

    private Main plugin;

    public tabWarps(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> output = new ArrayList<>();

        try {
            output.addAll(plugin.waystones.getConfig().getValues(false).keySet());
        } catch (Exception ignored) {}

        return output;
    }
}
