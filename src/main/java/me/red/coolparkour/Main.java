package me.red.coolparkour;

import me.red.coolparkour.commands.parkourCommand;
import me.red.coolparkour.listeners.onPlayerQuit;
import me.red.coolparkour.listeners.walkOnPressureListener;
import me.red.coolparkour.manager.ConfigManager;
import me.red.coolparkour.manager.ParkourManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {
    private static Main instance;
    public ConfigManager configManager = new ConfigManager(this);
    public HashMap<String,ParkourManager> StringParkourHashMap = new HashMap<>();
    public HashMap<UUID,ParkourManager> UUIDParkourHashMap = new HashMap<>();

    public static Main Instance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        configManager.setup();

        getCommand("parkour").setExecutor(new parkourCommand());
        getServer().getPluginManager().registerEvents(new walkOnPressureListener(),this);
        getServer().getPluginManager().registerEvents(new onPlayerQuit(),this);

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
