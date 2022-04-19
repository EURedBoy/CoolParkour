package me.red.coolparkour.manager;

import me.red.coolparkour.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ConfigManager {
    private final Main plugin;
    public HashMap<String,FileConfiguration> StringFileCFG = new HashMap<>();
    private HashMap<String,File> StringFile = new HashMap<>();

    public ConfigManager(Main plugin) {
        this.plugin = plugin;
    }


    // Files & File Configs Here
    public FileConfiguration fileConfiguration;
    public File file;
    // --------------------------

    public void setup() {

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        File checkPoints = new File(plugin.getDataFolder(),"checkpoints");

        if (!checkPoints.exists()) {
            checkPoints.mkdirs();
            return;
        }

        if (checkPoints.listFiles().length == 0)
            return;

        for (File file : checkPoints.listFiles()) {
            String fileName = file.getName().replaceAll(".yml","");
            StringFile.put(fileName,file);
            StringFileCFG.put(fileName,YamlConfiguration.loadConfiguration(file));
        }


    }

    public void addFile(String parkourName) {

        file = new File(plugin.getDataFolder(), "/checkpoints/" + parkourName + ".yml");

        if (file.exists()) {
            if (StringFile.containsValue(file))
                return;

            StringFileCFG.put(parkourName,fileConfiguration);
            StringFile.put(parkourName,file);
        }

        try {
            file.createNewFile();
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The file has been created");
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender()
                    .sendMessage(ChatColor.RED + "Could not create file");
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);

        fileConfiguration.set("Name",parkourName);

        StringFileCFG.put(parkourName,fileConfiguration);
        StringFile.put(parkourName,file);
    }

    public FileConfiguration getFileCFG(String parkourName) {
        return StringFileCFG.get(parkourName);
    }


    public void saveFile(String parkourName) {
        try {
            StringFileCFG.get(parkourName).save(StringFile.get(parkourName));
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the file");
        }
    }

}
