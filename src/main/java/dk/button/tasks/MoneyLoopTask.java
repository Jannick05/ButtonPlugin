package dk.button.tasks;

import dk.button.Button;
import dk.button.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

public class MoneyLoopTask implements Runnable {
    private final Econ econ = new Econ();
    Stats stats = new Stats();
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int multi = stats.getMulti(player);
            if (multi > 0) {
                econ.addMoney(player, multi+1);
            } else {
                econ.addMoney(player, 1);
            }
            //tjekker regoin og upgradere.
            for (Map.Entry<String, Cuboid> entry : Regions.regions.entrySet()) {
                String key = entry.getKey();
                Cuboid cuboid = entry.getValue();
                if (cuboid.isIn(player)) {
                    if(key.contains("knap")) {
                        int price = Button.configYML.getInt("regions."+key+".price");
                        int multiGet = Button.configYML.getInt("regions."+key+".multi");
                        if(Econ.getBalance(player) >= price) {
                            Econ.remMoney(player, price);
                            if(stats.getRebirth(player) >= 1) {
                                stats.addMulti(player, multiGet*(stats.getRebirth(player)+1));
                            } else {
                                stats.addMulti(player, multiGet);
                            }
                            String message = Chat.colored("&8[ &a&lUPGRADE &8] &fDin multiplier blev forøget til &a&l" + Format.format(stats.getMulti(player)));
                            ActionBar.sendActionBar(player, message);
                        } else {
                            String message = Chat.colored("&8[ &a&lUPGRADE &8] &cDu mangler &c&n" + Format.format((price - Econ.getBalance(player))) + "&c for at opgradere");
                            ActionBar.sendActionBar(player, message);
                        }

                    } else if(key.contains("rebirth")) {
                        int multiGet = Button.configYML.getInt("regions."+key+".multi");
                        int rebirthGet = Button.configYML.getInt("regions."+key+".rebirth");
                        if(multi >= multiGet) {
                            stats.remMulti(player, multiGet);
                            stats.addRebirth(player, rebirthGet);
                            String message = Chat.colored("&8[ &a&lUPGRADE &8] &fDin rebirth blev forøget til &a&l" + Format.format(stats.getRebirth(player)));
                            ActionBar.sendActionBar(player, message);
                        } else {
                            String message = Chat.colored("&8[ &a&lUPGRADE &8] &cDu mangler &c&n" + Format.format((multiGet - multi)) + "&c for at opgradere");
                            ActionBar.sendActionBar(player, message);
                        }
                    }
                }
            }
        }
    }
}
