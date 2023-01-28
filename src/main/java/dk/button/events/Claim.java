package dk.button.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Claim implements Listener {

    public static Map<String, Location> adminClaim = new HashMap<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();

        ItemStack item = p.getItemInHand();
        Material material = item.getType();
        p.sendMessage("&fBreak");
        if (p.isOp()) {
            p.sendMessage("&fBreak2");
            if (p.getGameMode() == GameMode.CREATIVE) {
                p.sendMessage("&fBreak3");
                if(material == Material.DIAMOND_AXE) {
                    p.sendMessage("&fBreak4");
                    event.setCancelled(true);
                    p.sendMessage("&fDu satte pos1 til &c" + event.getBlock().getLocation());
                    adminClaim.put("pos1", event.getBlock().getLocation());

                }

            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        ItemStack item = p.getItemInHand();
        Material material = item.getType();
        p.sendMessage(event.getAction().toString());
        if(event.getAction().toString().equals("RIGHT_CLICK_BLOCK")) {
            p.sendMessage("&fLeftClick1");
            if (p.isOp()) {
                p.sendMessage("&fLeftClick2");
                if (p.getGameMode() == GameMode.CREATIVE) {
                    p.sendMessage("&fLeftClick3");
                    if(material == Material.DIAMOND_AXE) {
                        p.sendMessage("&fLeftClick4");
                        event.setCancelled(true);
                        p.sendMessage("&fDu satte pos1 til &c" + event.getClickedBlock().getLocation());
                        adminClaim.put("pos2", event.getClickedBlock().getLocation());
                    }
                }
            }
        }
    }
}
