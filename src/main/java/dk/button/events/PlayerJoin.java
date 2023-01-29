package dk.button.events;

import dk.button.Button;
import dk.button.commands.Spawn;
import dk.button.utils.Chat;
import dk.button.utils.Stats;
import dk.button.utils.Title;
import dk.button.utils.board.Board;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    Button plugin;

    public PlayerJoin(Button plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayer(PlayerJoinEvent e) {

        Player p = (Player) e.getPlayer();
        Board board = new Board(p);
        board.updateTitle(Chat.colored("&a&lBUTTON &7&o("+Bukkit.getServer().getOnlinePlayers().size()+")"));
        plugin.boards.put(p.getUniqueId(), board);
        Title.sendTabTitle(p, "&a&lBUTTON &f&lSIMULATOR", "");

        p.teleport(Spawn.getSpawn());

        Stats stats = new Stats();

        if (!stats.hasAccount(p)) {
            stats.createAccount(p);
        } else {
            stats.loadAccount(p);
        }


        //Checks if the player has a permission or group, and then grants them the corresponding speed.
        if(p.hasPermission("group.vip")) {
            Bukkit.broadcastMessage("VIP JOINEDE");
            p.setWalkSpeed((float) 0.2888889);
        }

        if(p.hasPermission("speed")) {
            Bukkit.broadcastMessage("SPEED JOINEDE");
            p.setWalkSpeed((float) 0.4666667);
        }
    }
}
