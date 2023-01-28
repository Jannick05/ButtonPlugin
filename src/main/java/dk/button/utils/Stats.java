package dk.button.utils;

import dk.button.Button;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.UUID;

public class Stats {

    protected static HashMap<UUID, Number> statsMulti = new HashMap<>();


    public boolean createAccount(OfflinePlayer PlayerName)
    {
        if (!statsMulti.containsKey("Accounts."+PlayerName.getUniqueId()+".Multi")) {
            System.out.println("17");
            statsMulti.put(PlayerName.getUniqueId(), 0);
            Button.statsYML.set("Accounts."+PlayerName.getUniqueId()+".Multi", 0);
            Button.stats.saveConfig();
            return true;
        }
        System.out.println("23");
        return false;
    }

    public boolean addMulti(OfflinePlayer PlayerName, Integer Amount) {
        if (statsMulti.containsKey(PlayerName.getUniqueId())) {
            int result = (int) statsMulti.get(PlayerName.getUniqueId()) + Amount;

            statsMulti.put(PlayerName.getUniqueId(), result);
            Button.statsYML.set("Accounts."+ PlayerName.getUniqueId()+".Multi", result);
            Button.stats.saveConfig();
            return true;
        }
        return false;
    }
    public int getMutli(OfflinePlayer PlayerName) {
        if (statsMulti.containsKey(PlayerName.getUniqueId())) {
            return (int) statsMulti.get(PlayerName.getUniqueId());
        }
        return 0;
    }

    public boolean hasAccount(OfflinePlayer PlayerName) {
        return statsMulti.containsKey("Accounts."+PlayerName.getUniqueId()+".Multi");
    }


    public boolean deleteAccount(OfflinePlayer PlayerName) {
        if (statsMulti.containsKey(PlayerName.getUniqueId())) {
            statsMulti.remove(PlayerName.getUniqueId());

            Button.statsYML.set("Accounts."+ PlayerName.getUniqueId()+".Multi", null);

            return true;
        }
        return false;
    }

}
