package me.Dreamy.Recalls.Methods;

import me.Dreamy.Recalls.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Transport {

    public Player player;
    public Location location;
    private Main plugin;

    public static Map<UUID, Location> commencingTeleports = new HashMap<>();

    public Transport(Player player, Location location, Main plugin, boolean checkLocation) {
        this.player = player;
        this.location = location;
        this.plugin = plugin;

        String prefix = ChatColor.translateAlternateColorCodes('&', plugin.config.getConfig().getString("system.prefix"));

        commencingTeleports.put(this.player.getUniqueId(), this.location);

        int delay = plugin.config.getConfig().getInt("teleport.delay");
        if (player.hasPermission("rc.teleport.bypass.instant"))
            delay = 20;

        final int finalDelay = delay;

        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aTeleporting in &c"+ (((float) finalDelay) / 20) +"s&a..."));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            private boolean valid = true;
            private int i = 0;
            private Location old_location = player.getLocation();

            private boolean effects = plugin.config.getConfig().getBoolean("teleport.effects");

            private boolean privileged = player.hasPermission("rc.teleport.bypass.limit");

            @Override
            public void run() {
                if (valid) {
                    if(player.getLocation().getBlockX() != old_location.getBlockX() || player.getLocation().getBlockZ() != old_location.getBlockZ() || player.getLocation().getBlockY() != old_location.getBlockY()) {
                        valid = false;
                        commencingTeleports.remove(player.getUniqueId());

                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cTeleport cancelled because of movement!"));
                        return;
                    }
                    if(!privileged && !checkSky(player.getLocation())) {
                        valid = false;
                        commencingTeleports.remove(player.getUniqueId());

                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cCannot teleport because your sky access is blocked!"));
                        return;
                    }
                    if(!privileged && !checkSky(location) && checkLocation) {
                        valid = false;
                        commencingTeleports.remove(player.getUniqueId());

                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cCannot teleport because the destination's sky access is blocked!"));
                        return;
                    }
                    if (i == finalDelay) {

                        if (effects)
                            transportEffect(player.getLocation());
                        player.teleport(location);
                        if (effects)
                            transportEffect(player.getLocation());

                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aPoof!"));
                        valid = false;
                        commencingTeleports.remove(player.getUniqueId());
                        return;
                    }
                    if (effects) {
                        if (i == 1) {
                            for (Player p : Bukkit.getOnlinePlayers())
                            {
                                p.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 2, 1);
                            }
                        }

                        if (privileged) {
                            teleportingEffect(player.getLocation(), Particle.FLAME, i, finalDelay);
                        } else {
                            teleportingEffect(player.getLocation(), Particle.SPELL_WITCH, i, finalDelay);
                        }
                        teleportingEffect(location, Particle.CRIT_MAGIC, i, finalDelay);
                    }
                    i++;
                }
            }
        }, 0, 1);
    }

    public static boolean checkSky(Location location) {
        Block block = location.getBlock().getRelative(0, 1, 0);
        return (
                block.getLightFromSky() == 15 ||
                (location.getWorld().getEnvironment() == World.Environment.THE_END && location.getWorld().getHighestBlockAt(location).getY() <= location.getY())
        );
    }

    public static void teleportingEffect(Location location, Particle particle, double i, double m) {
        location.getWorld().spawnParticle(
                particle,
                location.getX()+(Math.sin(Math.pow((double)i/20d, 1.5d)*(100/m))*(140d/(120d+(double)i))),
                location.getY()+0.45,
                location.getZ()+(Math.cos(Math.pow((double)i/20d, 1.5d)*(100/m))*(140d/(120d+(double)i))),
                3,
                0,0.2,0,
                0
        );
        location.getWorld().spawnParticle(
                particle,
                location.getX()-(Math.sin(Math.pow((double)i/20d, 1.5d)*(100/m))*(140d/(120d+(double)i))),
                location.getY()+0.45,
                location.getZ()-(Math.cos(Math.pow((double)i/20d, 1.5d)*(100/m))*(140d/(120d+(double)i))),
                3,
                0,0.2,0,
                0
        );
        location.getWorld().spawnParticle(
                Particle.ENCHANTMENT_TABLE,
                location.getX(),
                location.getY()+0.3,
                location.getZ(),
                6,
                0.1,0.3,0.1
        );
    }

    public static void transportEffect(Location location) {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 0.5f);
            p.playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 4, 2f);
            p.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 3, 1f);
        }
        location.getWorld().spawnParticle(
                Particle.SPELL_WITCH,
                location.getX(),
                location.getY(),
                location.getZ(),
                8000,
                0.15,200,0.15
        );
        location.getWorld().spawnParticle(
                Particle.CLOUD,
                location.getX(),
                location.getY(),
                location.getZ(),
                100,
                0.2,0.2,0.2,
                0.2
        );
    }
}
