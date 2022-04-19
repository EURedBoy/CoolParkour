package me.red.coolparkour.listeners;

import me.red.coolparkour.Main;
import me.red.coolparkour.manager.ParkourManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onPlayerQuit implements Listener {
    Main plugin = Main.Instance();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        if (plugin.UUIDParkourHashMap.get(p.getUniqueId()) == null) return;

        ParkourManager parkourManager = plugin.UUIDParkourHashMap.get(p.getUniqueId());

        parkourManager.clearData(p.getUniqueId());

    }
}
