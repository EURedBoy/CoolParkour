package me.red.coolparkour.commands;

import me.red.coolparkour.Main;
import me.red.coolparkour.manager.CheckPointManager;
import me.red.coolparkour.manager.ParkourManager;
import me.red.coolparkour.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class parkourCommand implements CommandExecutor {
    Main plugin = Main.Instance();
    FileConfiguration config = plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            Message.send(sender,config.getString("console-error"));
            return false;
        }

        Player p = (Player)sender;

        if (!p.hasPermission("parkour.use")) {
            Message.send(p,"error-no-permission");
            return false;
        } //No permission

        if (args.length == 0) {
            Message.pluginInfo(p);
            return true;
        } //Send plugin info

        if (!(args.length > 1)) {
            Message.send(p,config.getString("error-args"));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                plugin.configManager.addFile(args[1]);
                plugin.StringParkourHashMap.put(args[1],
                        new ParkourManager(args[1],plugin.configManager.getFileCFG(args[1])));
                Message.send(p,config.getString("successfully-created"));
                break;
            case "set":
                if (!(args.length > 2)) {
                    Message.send(p,config.getString("error-args"));
                    return false;
                }

                switch (args[2]) {
                    case "start":
                        CheckPointManager.setStartCheckPoint(args[1],p.getLocation());
                        Message.send(p,config.getString("start-point"));
                        break;
                    case "end":
                        CheckPointManager.setEndCheckPoint(args[1],p.getLocation());
                        Message.send(p,config.getString("end-point"));
                        break;
                    case "checkpoint":
                        CheckPointManager.setCheckPoint(args[1],p.getLocation());
                        Message.send(p,config.getString("check-point"));
                        break;
                }
                break;
        }


        return true;
    }
}
