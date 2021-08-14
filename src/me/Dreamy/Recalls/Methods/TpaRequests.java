package me.Dreamy.Recalls.Methods;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.Dreamy.Recalls.Main;
import net.md_5.bungee.api.ChatColor;

public class TpaRequests {

    public static Map<String, String> request = new HashMap<String, String>();

    public Main plugin;
    public TpaRequests(Main plugin) {
        this.plugin = plugin;
    }

    public static boolean removeRequest(String key) {
        if(request.containsKey(key)) {

            request.remove(key);

            return true;
        }

        return false;
    }

    public static void sendRequest(Player p, Player p2) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bTPA request sent to &r" + p2.getName()));
        p2.sendMessage(ChatColor.translateAlternateColorCodes('&', ""
                + "&r" + p.getName() + " &bwants to recall on you.\n"
                + "&bType &a/tpaccept &bto accept\n"
                + "&bType &c/tpdeny &bto deny\n"
                + "&bRequest times out in &330 &bseconds."
        ));

        request.put(p2.getName(), p.getName());
    }
}
