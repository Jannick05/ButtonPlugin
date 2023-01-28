package dk.button.utils;

import dk.button.Button;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.UUID;

public class Stats {

    public static Map<UUID, Integer> statsMulti = new HashMap<>();


    public static boolean createAccount(OfflinePlayer PlayerName) {
        System.out.println("resat / ny account");
        statsMulti.put(PlayerName.getUniqueId(), 0);
        Button.statsYML.set("Accounts." + PlayerName.getUniqueId() + ".Multi", 0);
        Button.stats.saveConfig();
        System.out.println("statsMulti" + statsMulti);
        return true;
    }
    public static boolean loadAccount(OfflinePlayer PlayerName) {
        System.out.println("allerede account - multi: " + Button.statsYML.get("Accounts."+PlayerName.getUniqueId()+".Multi"));
        statsMulti.put(PlayerName.getUniqueId(), (Integer) Button.statsYML.get("Accounts."+PlayerName.getUniqueId()+".Multi"));
        return true;
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

            Button.statsYML.set("Accounts."+ PlayerName.getUniqueId(), null);
            if (PlayerName.isOnline()) {
                Stats.createAccount(PlayerName);
            }

            return true;
        }
        return false;
    }

}
