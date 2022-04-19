package me.red.coolparkour.listeners;

import me.red.coolparkour.Main;
import me.red.coolparkour.manager.CheckPointManager;
import me.red.coolparkour.manager.ParkourManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class walkOnPressureListener implements Listener {
    Main plugin = Main.Instance();

    @EventHandler
    public void onPressure(PlayerInteractEvent e) {

        if (!e.getAction().equals(Action.PHYSICAL)) return;
        if (!(e.getClickedBlock().getType() == Material.IRON_PLATE)) return;

        Player p = e.getPlayer();

        if (plugin.UUIDParkourHashMap.get(p.getUniqueId()) == null
                && CheckPointManager.isStartCheckPoint(p.getLocation(),p))
            return; //Game start

        if (plugin.UUIDParkourHashMap.get(p.getUniqueId()) != null &&
                CheckPointManager.isEndCheckPoint(p.getLocation(),p))
            return; //Game end

        if (plugin.UUIDParkourHashMap.get(p.getUniqueId()) != null &&
                CheckPointManager.isCheckPoint(p.getLocation(),p))
            return; //Take checkpoint







    }
}
