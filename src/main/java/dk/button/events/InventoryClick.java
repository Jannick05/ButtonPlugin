package dk.button.events;

import dk.button.commands.Spawn;
import dk.button.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Collection;

public class InventoryClick implements Listener {
    

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();

        if(e.getInventory().getTitle().equals(Chat.colored("&a&lBUY"))) {
            e.setCancelled(true);

            if(e.getCurrentItem().getType() != Material.AIR) {
                p.closeInventory();
                p.sendMessage(Chat.colored("&8[ &a&lBUY &8] &fEn staff vil kontakte dig snarest muligt!"));
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 1f);
            }

            if(e.getSlot() == 0) {
                sendToOp("&8[ &c&lADMIN &8] &a&l" + p.getName() + "&f vil gerne købe &a&lVIP");
            }

            if(e.getSlot() == 1 ) {
                sendToOp("&8[ &c&lADMIN &8] &b&l" + p.getName() + "&f vil gerne købe &b&lSPEED");
            }

            if(e.getSlot() == 2 ) {
                sendToOp("&8[ &c&lADMIN &8] &b&l" + p.getName() + "&f vil gerne købe en &c&lREJSE &f&lBOG");
            }
            
        } else if (e.getInventory().getTitle().equals(Chat.colored("&c&lREJSE &f&lBOGEN"))) {
            e.setCancelled(true);

            if(e.getSlot() == 0) {
                p.teleport(Spawn.getSpawn());
                p.closeInventory();
                p.sendMessage(Chat.colored("&8[ &c&lREJSE &f&lBOGEN &8] &fDu er ankommet til " + e.getCurrentItem().getItemMeta().getDisplayName()));
            }
        }
    }

    private void sendToOp(String s) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.isOp()) {
                player.sendMessage(Chat.colored(s));
            }
        }
    }
}
