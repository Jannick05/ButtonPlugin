package dk.button.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static dk.button.Button.config;
import static dk.button.Button.configYML;

public class SetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission("button.setspawn")) {
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            float yaw = player.getLocation().getYaw();
            float pitch = player.getLocation().getPitch();
            configYML.set("spawn.x", x);
            configYML.set("spawn.y", y);
            configYML.set("spawn.z", z);
            configYML.set("spawn.yaw", yaw);
            configYML.set("spawn.pitch", pitch);
            player.sendMessage("Spawn set to " + x + "," + y + "," + z + yaw + "," + pitch);
            config.saveConfig();
            return true;
        }
        return false;
    }

}
