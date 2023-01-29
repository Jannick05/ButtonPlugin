package dk.button.events;

import dk.button.utils.Chat;
import dk.button.utils.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener {
    private Inventory inv = null;

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {
        try {
            Player p = e.getPlayer();
            ItemStack i = e.getItem();
            if (i.toString() == "null") {
                return;
            }

            if (i.getItemMeta().getDisplayName().equals(Chat.colored("&c&lREJSE &f&lBOGEN"))) {
                inv = Bukkit.createInventory(null, 9*1, Chat.colored("&c&lREJSE &f&lBOGEN"));
                inv.setItem(0, GUI.createItemStack(new ItemStack(Material.GRASS), Chat.colored("&2&lGRASSLANDS"), Chat.colored("&7"), Chat.colored("&fAdang:"), Chat.colored("&8&l» &aJa"), Chat.colored("&7")));

                p.openInventory(inv);
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }
}
