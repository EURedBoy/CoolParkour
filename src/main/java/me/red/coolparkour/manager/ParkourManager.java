package me.red.coolparkour.manager;

import me.red.coolparkour.Main;
import me.red.coolparkour.utils.Message;
import me.red.coolparkour.utils.TimeUtils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class ParkourManager {
    private static final Main plugin = Main.Instance();
    private static final FileConfiguration config = plugin.getConfig();
    private static final TimeUtils timeUtils = new TimeUtils();

    public String name;
    public FileConfiguration fileCFG;
    private HashMap<UUID,Long> UUIDLongHashMap = new HashMap<>();
    public HashMap<UUID, Location> UUIDLocationHashMap = new HashMap<>();
    private List<UUID> inGame = new ArrayList<>();

    public ParkourManager(String name, FileConfiguration fileCFG) {
        this.name = name;
        this.fileCFG = fileCFG;
    }

    public void Start(Player p) {

        UUIDLongHashMap.put(p.getUniqueId(),System.currentTimeMillis());
        plugin.UUIDParkourHashMap.put(p.getUniqueId(),this);

        inGame.add(p.getUniqueId());

        Message.send(p,config.getString("on-start-parkour"));
    }

    public void CheckPoint(Player p) {

        UUIDLocationHashMap.put(p.getUniqueId(),p.getLocation());

        Message.send(p,config.getString("take-checkpoint")
                .replaceAll("%time%",timeUtils.getTimeTook(UUIDLongHashMap.get(p.getUniqueId()))));
    }

    public void End(Player p) {

        if (!inGame.contains(p.getUniqueId())) return;

        inGame.remove(p.getUniqueId());

        p.sendTitle(config.getString("end-title"),
                "§eTempo: §6");

        Message.send(p,config.getString("end-message").replaceAll("%time%",
                timeUtils.getTimeTook(UUIDLongHashMap.get(p.getUniqueId()))));

        clearData(p.getUniqueId());

    }

    public void clearData(UUID uuid) {

        UUIDLocationHashMap.remove(uuid);
        UUIDLongHashMap.remove(uuid);
        inGame.remove(uuid);
        plugin.UUIDParkourHashMap.remove(uuid);

    }
}
