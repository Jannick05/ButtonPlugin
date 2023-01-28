package dk.button.tasks;

import dk.button.Button;
import dk.button.utils.Econ;
import dk.button.utils.Stats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MoneyLoopTask implements Runnable {
    private Econ econ = new Econ();
    Stats stats = new Stats();
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int multi = stats.getMutli(player);
            if (multi > 0) {
                econ.addMoney(player, multi+1);
            } else {
                econ.addMoney(player, 1);
            }
        }
    }
}
