package dk.button.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static dk.button.Button.configYML;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.teleport(getSpawn());
        return false;
    }
    public static Location getSpawn() {
        double x = Double.parseDouble(configYML.get("spawn.x").toString());
        double y = Double.parseDouble(configYML.get("spawn.y").toString());
        double z = Double.parseDouble(configYML.get("spawn.z").toString());
        float yaw = Float.parseFloat(configYML.get("spawn.yaw").toString());
        float pitch = Float.parseFloat(configYML.get("spawn.pitch").toString());


        Location spawnPoint = new Location(Bukkit.getWorld("World"), x, y, z, yaw, pitch);
        return spawnPoint;
    }
}
