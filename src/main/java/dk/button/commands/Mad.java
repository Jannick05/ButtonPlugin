package dk.button.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Mad implements CommandExecutor {
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
        return true;
    }
}
