package me.Dreamy.Recalls.TabCompleters;

import me.Dreamy.Recalls.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class tabBeds implements TabCompleter {

    private Main plugin;

    public tabBeds(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return null;
        Player player = (Player) sender;

        List<String> output = new ArrayList<>();

        if (args.length == 1) {
            MemorySection find = (MemorySection) plugin.playerData.getConfig().get(player.getUniqueId() + ".beds");
            if (find != null) {
                output.addAll(find.getValues(false).keySet());
            }
        }

        return output;
    }
}
