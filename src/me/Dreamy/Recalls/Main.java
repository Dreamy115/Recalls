package me.Dreamy.Recalls;

import me.Dreamy.Recalls.Commands.*;
import me.Dreamy.Recalls.Commands.TPA.TPA;
import me.Dreamy.Recalls.Commands.TPA.TPAccepted;
import me.Dreamy.Recalls.Commands.TPA.TPDenied;
import me.Dreamy.Recalls.Data.Manager;
import me.Dreamy.Recalls.Methods.BedManager;
import me.Dreamy.Recalls.Methods.Transport;
import me.Dreamy.Recalls.TabCompleters.tabBeds;
import me.Dreamy.Recalls.TabCompleters.tabPlayerList;
import me.Dreamy.Recalls.TabCompleters.tabWarps;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    public Manager config = new Manager(this, "config.yml");
    public Manager waystones = new Manager(this, "waystones.yml");
    public Manager playerData = new Manager(this, "players.yml");

    @Override
    public void onEnable() {
        // Load configs
        config.reloadConfig();
        waystones.reloadConfig();
        playerData.reloadConfig();

        // Apply command Executors
        getCommand("rcreload").setExecutor(new cmdReload(this));
        getCommand("recall").setExecutor(new cmdRecall(this));
        getCommand("bed").setExecutor(new cmdBed(this));
        getCommand("delbed").setExecutor(new cmdBedUnlink(this));

        getCommand("tpa").setExecutor(new TPA(this));
        getCommand("tpaccept").setExecutor(new TPAccepted(this));
        getCommand("tpdeny").setExecutor(new TPDenied(this));

        getCommand("setwarp").setExecutor(new cmdSetwarp(this));
        getCommand("delwarp").setExecutor(new cmdDelwarp(this));

        // Tab completers
        getCommand("bed").setTabCompleter(new tabBeds(this));
        getCommand("delbed").setTabCompleter(new tabBeds(this));
        getCommand("tpa").setTabCompleter(new tabPlayerList());

        getCommand("recall").setTabCompleter(new tabWarps(this));
        getCommand("delwarp").setTabCompleter(new tabWarps(this));
        getCommand("setwarp").setTabCompleter(new tabWarps(this));

        // Register self
        Bukkit.getPluginManager().registerEvents(this, this);

        // Register other listeners
        Bukkit.getPluginManager().registerEvents(new BedManager(this), this);
    }

    @EventHandler
    void onPlayerQuit (PlayerQuitEvent event) {
        Transport.commencingTeleports.remove(event.getPlayer().getUniqueId());
    }

    @Override
    public void onDisable() {
        Transport.commencingTeleports.clear();
    }
}
