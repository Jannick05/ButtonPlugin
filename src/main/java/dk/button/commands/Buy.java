package dk.button.commands;

import dk.button.utils.Chat;
import dk.button.utils.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Buy implements CommandExecutor {
    private Inventory inv = null;

    // TODO LIST
    //  - VIP: 1.5x stats, walking speed 4, prefix, trail.
    //  - 2x speed: walking speed 2.


    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Player p = (Player) sender;


        //BUY GUI
        ItemStack button_head = GUI.getSkull("http://textures.minecraft.net/texture/96c4ac00736d8551c8ced4cf5d852c05fb8fde743deca6da334c8154b2eec3f7");
        ItemStack speed_head = GUI.getSkull("http://textures.minecraft.net/texture/69e5fb313510bbb0b22c3500b425e30fce35bd97177ab8d2fdd61946cccd4bf0");

        inv = Bukkit.createInventory(null, 9*1, Chat.colored("&a&lBUY"));
        inv.setItem(0, GUI.createItemStack(button_head, Chat.colored("&a&lVIP"), "&7", "&fIndeholder:", "&8&l» &71.5x multi på alle stats", "&8&l» &7speed 4", "&8&l» &7Prefix: &a&lVIP " + p.getName()));
        inv.setItem(1, GUI.createItemStack(speed_head, Chat.colored("&b&lSPEED"), "&7", "&fIndeholder:", "8&l» &7speed 5"));

        p.openInventory(inv);

        //https://textures.minecraft.net/texture/36f59da67b36a8df3364bb64aeb7f074baa460d596614f3a58be90cdca272297


        return true;
    }
}

