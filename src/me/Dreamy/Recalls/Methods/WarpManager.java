package me.Dreamy.Recalls.Methods;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.Dreamy.Recalls.Data.Manager;

public class WarpManager {

    public static class Waystone {

        public Manager data;
        public String name;
        public int x;
        public double y;
        public int z;
        public String world;
        public float pitch;
        public float yaw;

        public Waystone(Manager data, String name, int x, double y, int z, String world, float pitch, float yaw) {
            this.data = data;
            this.name = name;
            this.x = x;
            this.y = y;
            this.z = z;
            this.world = world;
            this.pitch = pitch;
            this.yaw = yaw;
        }

        public Waystone(Manager data, String name, Location location) {
            this.data = data;
            this.name = name;
            this.x = location.getBlockX();
            this.y = location.getY();
            this.z = location.getBlockZ();
            this.world = location.getWorld().getName();
            this.pitch = location.getPitch();
            this.yaw = location.getYaw();
        }

        public Waystone(Manager data, String name) throws NullPointerException {
            if (data.getConfig().get(name) == null) {
                throw new NullPointerException("Couldn't find warp");
            }

            this.data = data;
            this.name = name;
            this.x = data.getConfig().getInt(name + ".x");
            this.y = data.getConfig().getDouble(name + ".y");
            this.z = data.getConfig().getInt(name + ".z");
            this.world = data.getConfig().getString(name + ".world");
            this.pitch = (float) data.getConfig().getDouble(name + ".pitch");
            this.yaw = (float) data.getConfig().getDouble(name + ".yaw");
        }

        public void set() {
            Bukkit.getLogger().log(Level.INFO, "Setting Waystone: " + name);
            data.getConfig().options().copyDefaults(true);
            data.getConfig().set(name + ".x", (x));
            data.getConfig().set(name + ".y", (y));
            data.getConfig().set(name + ".z", (z));
            data.getConfig().set(name + ".world", (world));
            data.getConfig().set(name + ".pitch", ((double) pitch));
            data.getConfig().set(name + ".yaw", ((double) yaw));

            data.saveConfig();
        }

        public void delete() {
            Bukkit.getLogger().log(Level.INFO, "Deleting Waystone: " + name);
            data.getConfig().options().copyDefaults(true);
            data.getConfig().set(name, (null));

            data.saveConfig();
        }

        public Location getLocation() {
            return new Location(
                    Bukkit.getWorld(world),
                    x+0.5,y,z+0.5,yaw,pitch
            );
        }

    };

    public static void onRespawn(PlayerRespawnEvent event, Manager data) {
        Waystone warp = new Waystone(data, "spawn");
        if(warp.world != null)
            event.setRespawnLocation(warp.getLocation());
    }

}
