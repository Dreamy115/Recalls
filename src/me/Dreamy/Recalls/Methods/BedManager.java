package me.Dreamy.Recalls.Methods;

import me.Dreamy.Recalls.Data.Manager;
import me.Dreamy.Recalls.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.MemorySection;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class BedManager implements Listener {

    private Main plugin;

    public BedManager(Main plugin) {
        this.plugin = plugin;
    }

    public static class PlayerBed {

        public Manager data;
        public int x;
        public int y;
        public int z;
        public String world;
        public String name;
        public UUID playerId;

        public PlayerBed(Manager data, UUID playerId, String name, int x, int y, int z, String world) {
            this.data = data;
            this.playerId = playerId;
            this.x = x;
            this.y = y;
            this.z = z;
            this.world = world;
            this.name = name;
        }
        public PlayerBed(Manager data, UUID playerId, String name) throws NullPointerException {
            this.data = data;
            this.playerId = playerId;
            this.name = name;

            if (data.getConfig().get(getID()) == null) throw new NullPointerException("Bed not found");
            this.x = data.getConfig().getInt(getID() + ".x");
            this.y = data.getConfig().getInt(getID() + ".y");
            this.z = data.getConfig().getInt(getID() + ".z");
            this.world = data.getConfig().getString(getID() + ".world");
        }

        String getID() {
            return playerId + ".beds." + name;
        }

        public void set() {
            data.getConfig().options().copyDefaults(true);

            data.getConfig().set(getID() + ".x", x);
            data.getConfig().set(getID() + ".y", y);
            data.getConfig().set(getID() + ".z", z);
            data.getConfig().set(getID() + ".world", world);

            data.saveConfig();
        }

        public void delete() {
            data.getConfig().set(getID(), null);
            data.saveConfig();
        }

        public boolean checkIntegrity() {
            Block bed = Bukkit.getServer().getWorld(world).getBlockAt(this.getLocation());

            if (world == null) {
                this.delete();
                return false;
            }

            if (!(bed.getBlockData() instanceof org.bukkit.block.data.type.Bed)) {
                this.delete();
                return false;
            }

            return (
                    bed.getRelative(0, 1, 0).getType() == Material.AIR &&
                    bed.getRelative(0, 2, 0).getType() == Material.AIR
            );
        }

        public Location getLocation() {
            return new Location(
                    Bukkit.getWorld(world),
                    ((double) x) + 0.5,((double) y) + 0.563,((double) z) + 0.5
            );
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();

        if (player.getWorld().getEnvironment().equals(World.Environment.NETHER) || player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
            return;
        }

        if (!(player.hasPermission("rc.bed.use"))) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cNot enough permissions to set home."));
            return;
        }

        Map<String, Integer> world_count = new HashMap<>(Math.max(plugin.config.getConfig().getInt("beds.total"), 10));
        int count = 0;

        try {
            MemorySection find = (MemorySection) plugin.playerData.getConfig().get(player.getUniqueId() + ".beds");

            if (find != null) {
                for (String b : find.getValues(false).keySet()) {
                    try {
                        PlayerBed bed = new PlayerBed(
                                plugin.playerData,
                                player.getUniqueId(),
                                b
                        );
                        if (
                                bed.getLocation().getBlockX() == event.getBed().getLocation().getBlockX() &&
                                bed.getLocation().getBlockY() == event.getBed().getLocation().getBlockY() &&
                                bed.getLocation().getBlockZ() == event.getBed().getLocation().getBlockZ() &&
                                bed.world.equals(event.getBed().getLocation().getWorld().getName())
                        ) {
                            return;
                        }

                        bed.checkIntegrity();

                        world_count.put(bed.world, world_count.getOrDefault(bed.world, 0) + 1);
                        count++;
                    } catch (Exception ignored) {

                    }
                }

                int max = plugin.config.getConfig().getInt("beds.total");
                if (player.hasPermission("rc.bed.bypass"))
                    max = -1;
                if (max >= 0 && count >= max) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cToo many beds. Please delete some to be able to set new ones."));
                    return;
                }
                for (Integer w : world_count.values()) {
                    int wmax = plugin.config.getConfig().getInt("beds.world");
                    if (player.hasPermission("rc.bed.bypass"))
                        wmax = -1;
                    if (wmax >= 0 && w >= wmax) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&cYou cannot have more beds in this world."));
                        return;
                    }
                }
            }
        } catch (NullPointerException ignored) {}
        int key = world_count.getOrDefault(player.getWorld().getName(), 0);

        if (plugin.playerData.getConfig().get(player.getUniqueId() + ".beds." + player.getWorld().getName() + "_" + key) != null) {
            key++;
        }

        PlayerBed bed = new PlayerBed(plugin.playerData,
                player.getUniqueId(), player.getWorld().getName() + "_" + key,
                event.getBed().getLocation().getBlockX(),
                event.getBed().getLocation().getBlockY(),
                event.getBed().getLocation().getBlockZ(),
                event.getBed().getWorld().getName()
        );

        bed.set();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix") + "&aBed Linked! Recall here with &b/bed " + bed.name));
    }
}
