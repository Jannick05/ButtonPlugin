package dk.button.tasks;

import dk.button.Button;
import dk.button.utils.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

public class MoneyLoopTask implements Runnable {
    private Econ econ = new Econ();
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
                            stats.addMulti(player, multiGet);
                            String message = Chat.colored("&8[ &a&lUPGRADE &8] &fDin multiplier blev forøget til &a&l" + stats.getMulti(player));
                            ActionBar.sendActionBar(player, message);
                        } else {
                            String message = Chat.colored("&8[ &a&lUPGRADE &8] &cDu mangler &c&n" + (price - Econ.getBalance(player)) + "&c for at opgradere");
                            ActionBar.sendActionBar(player, message);
                        }

                    }
                }
            }
        }
    }
}
