package dk.button.commands;

import dk.button.Button;
import dk.button.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Mad implements CommandExecutor {
    private final HashMap<String, Long> cooldowns = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        int cooldownTime = Button.configYML.getInt("cooldown.time", 10);

        if(cooldowns.containsKey(p.getName())) {
            long secondsLeft = ((cooldowns.get(sender.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
            if(secondsLeft > 0) {
                sender.sendMessage(Chat.colored("&fDu skal vente &c" + secondsLeft + "&f sekunder!"));
                return true;
            }
        }
        cooldowns.put(p.getName(), System.currentTimeMillis());


        p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
        return true;
    }
}
