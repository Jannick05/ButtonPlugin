package dk.button.events;

import dk.button.utils.Stats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {


    @EventHandler
    public void onPlayer(PlayerJoinEvent e) {
        Stats stats = new Stats();
        Player p = (Player) e.getPlayer();
        System.out.println("Stats.hasAccount + " + stats.hasAccount(p));
        if (!stats.hasAccount(p)) {
            stats.createAccount(p);
        }
    }
}
