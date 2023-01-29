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
        ItemStack speed_head = GUI.getSkull("http://textures.minecraft.net/texture/42424acdc6e09a212b5c77c2c1d9abdcd433d3865ded33958a57f4204ef3278a");
        ItemStack rejsebog_head = GUI.getSkull("http://textures.minecraft.net/texture/ed88a86445d4114b15171bb9ef83746060cafff2cb2aa751159740cff48df89e");

        inv = Bukkit.createInventory(null, 9*1, Chat.colored("&a&lBUY"));
        inv.setItem(0, GUI.createItemStack(button_head, Chat.colored("&a&lVIP"), Chat.colored("&7"), Chat.colored("&fKoster: &a7.500 Emeralder"), Chat.colored("&7"), Chat.colored("&fFordele:"), Chat.colored("&8&l» &f1.5x multiplier på alle stats"), Chat.colored("&8&l» &fSpeed 2"), Chat.colored("&8&l» &fPrefix: &a&lVIP &f" + p.getName()), Chat.colored("&7")));
        inv.setItem(1, GUI.createItemStack(speed_head, Chat.colored("&b&lSPEED"), Chat.colored("&7"), Chat.colored("&fKoster: &a1.500 Emeralder"), Chat.colored("&7"), Chat.colored("&fFordele:"), Chat.colored("&8&l» &fSpeed 4"), Chat.colored("&7")));
        inv.setItem(2, GUI.createItemStack(rejsebog_head, Chat.colored("&c&lREJSE &f&lBOG"), Chat.colored("&7"), Chat.colored("&fKoster: &a1.000 Emeralder"), Chat.colored("&7"), Chat.colored("&fFordele:"), Chat.colored("&8&l» &fFast Travel"), Chat.colored("&7")));

        p.openInventory(inv);

        //https://textures.minecraft.net/texture/36f59da67b36a8df3364bb64aeb7f074baa460d596614f3a58be90cdca272297


        return true;
    }
}

