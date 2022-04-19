package me.red.coolparkour.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

    public static void send(CommandSender sender, String mess) {
        sender.sendMessage(color(mess));
    }

    public static void send(Player player, String mess) {
        player.sendMessage(color(mess));
    }

    public static String color(String mess) {
        return mess.replaceAll("&","§");
    }

    public static void pluginInfo(Player p) {
        p.sendMessage("§c<----§bParkour§c---->");
        p.sendMessage("§a(§e!§a) §b/parkour create §a<nome-parkour> §3Per creare il parkour");
    }
}
