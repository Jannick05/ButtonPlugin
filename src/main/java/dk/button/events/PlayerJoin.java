package dk.button.events;

import dk.button.Button;
import dk.button.commands.Spawn;
import dk.button.utils.Chat;
import dk.button.utils.Stats;
import dk.button.utils.Title;
import dk.button.utils.board.Board;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


import java.util.Collection;

import static dk.button.Button.luckPerms;

public class PlayerJoin implements Listener {
    Button plugin;

    public PlayerJoin(Button plugin) {
        this.plugin = plugin;
    }

    public static String getPlayerGroup(Player player, Collection<String> possibleGroups) {
        for (String group : possibleGroups) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }
        return null;
    }

    @EventHandler
    public void onPlayer(PlayerJoinEvent e) {

        Player p = (Player) e.getPlayer();
        e.setJoinMessage(Chat.colored("&a " + p));
        Board board = new Board(p);
        board.updateTitle(Chat.colored("&a&lBUTTON &7&o("+Bukkit.getServer().getOnlinePlayers().size()+")"));
        plugin.boards.put(p.getUniqueId(), board);
        Title.sendTabTitle(p, "&a&lBUTTON &f&lSIMULATOR", "");

        System.out.println("SPILLER JOIN EVENT ------------");
        p.teleport(Spawn.getSpawn());

        Stats stats = new Stats();

        if (!stats.hasAccount(p)) {
            stats.createAccount(p);
        } else {
            stats.loadAccount(p);
        }


        //Checks if the player has a permission or group, and then grants them the corresponding speed.


        if(p.hasPermission("group.vip")) {
            p.setWalkSpeed((float) 0.2888889);
        }

        if(p.hasPermission("speed")) {
            p.setWalkSpeed((float) 0.4666667);
        }

        if(p.isOp()) {
            addExtraName(p, Chat.colored("&c&lEJER"));
        }

    }



    public void addExtraName(Player player, String extraName) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(extraName, "dummy");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        player.setScoreboard(scoreboard);
        objective.getScore(extraName).setScore(9999);


    }

}
