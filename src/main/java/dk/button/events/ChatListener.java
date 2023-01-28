package dk.button.events;

import dk.button.Button;
import dk.button.utils.Chat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = (Player) event.getPlayer();
        if (p.isOp()) {
            event.setMessage(Chat.colored(event.getMessage()));
        } else {
            event.setMessage(event.getMessage());
        }

    }
}
