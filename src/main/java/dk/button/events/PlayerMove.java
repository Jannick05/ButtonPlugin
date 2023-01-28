package dk.button.events;

//import dk.button.utils.Cuboid;
import dk.button.Button;
import dk.button.utils.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Map;

public class PlayerMove implements Listener {
    Stats stats = new Stats();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        for (Map.Entry<String, Cuboid> entry : Regions.regions.entrySet()) {
            String key = entry.getKey();
            Cuboid cuboid = entry.getValue();
            if (cuboid.isIn(event.getPlayer())) {
                if(key.contains("knap")) {
                    int price = Button.configYML.getInt("regions."+key+".price");
                    int multi = Button.configYML.getInt("regions."+key+".multi");
                    if(Econ.getBalance(event.getPlayer()) >= price) {
                        Econ.remMoney(event.getPlayer(), price);
                        stats.addMulti(event.getPlayer(), multi);
                        event.getPlayer().sendMessage(Chat.colored("Opgraderede, ny multi = &a&n" + stats.getMutli(event.getPlayer())));
                        
                    } else {
                        event.getPlayer().sendMessage(Chat.colored("&fDu mangler &c&o(" + (price - Econ.getBalance(event.getPlayer())) + "&c&o)"));
                    }

                }
            }
        }
    }
}
