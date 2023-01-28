package dk.button.commands;

import dk.button.utils.Chat;
import dk.button.utils.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Buy implements CommandExecutor {
    private Inventory inv = null;

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Player p = (Player) sender;


        //BUY GUI
        inv = Bukkit.createInventory(null, 1, Chat.colored("&a&lBUY"));

        //ItemStack button_head = GUI.getSkull("tps://textures.minecraft.net/texture/36f59da67b36a8df3364bb64aeb7f074baa460d596614f3a58be90cdca272297");
        //inv.setItem(0, GUI.createItemStack(button_head, Chat.colored("&a&lBUTTON&2&l+"), "Test1", "Test2", "Test3"));
        inv.setItem(0, GUI.createGuiItem(Material.SANDSTONE_WALL, "LORt"));

        p.openInventory(inv);

        //https://textures.minecraft.net/texture/36f59da67b36a8df3364bb64aeb7f074baa460d596614f3a58be90cdca272297


        return true;
    }
}

