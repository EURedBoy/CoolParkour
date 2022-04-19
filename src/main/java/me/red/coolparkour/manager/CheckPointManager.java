package me.red.coolparkour.manager;

import me.red.coolparkour.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;


public class CheckPointManager {
    private static final Main plugin = Main.Instance();
    private static final ConfigManager configManager = plugin.configManager;
    private static Set<String> keys;

    public static void setStartCheckPoint(String parkourName, Location loc) {

        FileConfiguration config = configManager.getFileCFG(parkourName);
        String path = "start.";

        config.set(path + "x", (int)loc.getX());
        config.set(path + "y", (int)loc.getY());
        config.set(path + "z", (int)loc.getZ());
        config.set(path + "yaw", loc.getYaw());
        config.set(path + "pitch", loc.getPitch());
        config.set(path + "world", loc.getWorld().getName());

        configManager.saveFile(parkourName);
    }

    public static void setEndCheckPoint(String parkourName, Location loc) {

        FileConfiguration config = configManager.getFileCFG(parkourName);
        String path = "end.";

        config.set(path + "x", (int)loc.getX());
        config.set(path + "y", (int)loc.getY());
        config.set(path + "z", (int)loc.getZ());
        config.set(path + "yaw", loc.getYaw());
        config.set(path + "pitch", loc.getPitch());
        config.set(path + "world", loc.getWorld().getName());

        configManager.saveFile(parkourName);
    }

    public static void setCheckPoint(String parkourName, Location loc) {

        FileConfiguration config = configManager.getFileCFG(parkourName);

        String path = "";

        try {
            keys = config.getConfigurationSection("checkpoints").getKeys(false);
            path = "checkpoints." + (keys.size()+1) + ".";
        } catch (NullPointerException e) {
            path = "checkpoints.1.";
        }

        config.set(path + "x", (int)loc.getX());
        config.set(path + "y", (int)loc.getY());
        config.set(path + "z", (int)loc.getZ());
        config.set(path + "yaw", loc.getYaw());
        config.set(path + "pitch", loc.getPitch());
        config.set(path + "world", loc.getWorld().getName());

        configManager.saveFile(parkourName);

    }

    public static boolean isStartCheckPoint(Location loc, Player p) {

        String path = "start.";

        for (FileConfiguration file : plugin.configManager.StringFileCFG.values())
            if (file.get(path + "x").equals((int)loc.getX())
                    && file.get(path + "y").equals((int)loc.getY())
                    && file.get(path + "z").equals((int)loc.getZ())
                    && file.get(path + "world").equals(loc.getWorld().getName())) {

                String fileName = file.getString("Name");

                plugin.StringParkourHashMap.get(fileName).Start(p);

                return true;
            }

        return false;

    }

    public static boolean isEndCheckPoint(Location loc, Player p) {

        String path = "end.";
        ParkourManager parkourManager = plugin.UUIDParkourHashMap.get(p.getUniqueId());

        FileConfiguration file = parkourManager.fileCFG;

            if (file.get(path + "x").equals((int)loc.getX())
                    && file.get(path + "y").equals((int)loc.getY())
                    && file.get(path + "z").equals((int)loc.getZ())
                    && file.get(path + "world").equals(loc.getWorld().getName())) {

                parkourManager.End(p);

                return true;
            }

        return false;

    }

    public static boolean isCheckPoint(Location loc, Player p) {

        ParkourManager parkourManager = plugin.UUIDParkourHashMap.get(p.getUniqueId());

        FileConfiguration file = parkourManager.fileCFG;

        try {
            keys = file.getConfigurationSection("checkpoints").getKeys(false);
        } catch (NullPointerException e) {
            return false;
        }

        for (String key : keys) {
            String path = "checkpoints." + key + ".";

            if (file.get(path + "x").equals((int)loc.getX())
                    && file.get(path + "y").equals((int)loc.getY())
                    && file.get(path + "z").equals((int)loc.getZ())
                    && file.get(path + "world").equals(loc.getWorld().getName())) {

                parkourManager.CheckPoint(p);

                return true;
            }
        }

        return false;

    }
}
