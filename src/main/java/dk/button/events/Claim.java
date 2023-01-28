package dk.button.events;

import dk.button.utils.Chat;
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

        Double x = event.getBlock().getLocation().getX();
        Double y = event.getBlock().getLocation().getY();
        Double z = event.getBlock().getLocation().getZ();

        if (p.isOp() && p.getGameMode() == GameMode.CREATIVE && material == Material.DIAMOND_AXE) {
            event.setCancelled(true);
            p.sendMessage(Chat.colored("&8(&4&lBUTTONS&8) &7Du satte pos 1 til (" + x + ", " + y + ", " + z + ")"));
            adminClaim.put("pos1", event.getBlock().getLocation());

        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        ItemStack item = p.getItemInHand();
        Material material = item.getType();

        if(event.getAction().toString().equals("RIGHT_CLICK_BLOCK")) {

            Double x = event.getClickedBlock().getLocation().getX();
            Double y = event.getClickedBlock().getLocation().getY();
            Double z = event.getClickedBlock().getLocation().getZ();

            if (p.isOp() && p.getGameMode() == GameMode.CREATIVE && material == Material.DIAMOND_AXE) {
                event.setCancelled(true);
                p.sendMessage(Chat.colored("&8(&4&lBUTTONS&8) &7Du satte pos 2 til (" + x + ", " + y + ", " + z + ")"));
                adminClaim.put("pos2", event.getClickedBlock().getLocation());
            }
        }
    }
}
