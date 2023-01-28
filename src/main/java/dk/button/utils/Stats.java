package dk.button.utils;

import dk.button.Button;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.UUID;

public class Stats {

    public static Map<UUID, Integer> statsMulti = new HashMap<>();
    public static Map<UUID, Integer> statsRebirth = new HashMap<>();


    public static boolean createAccount(OfflinePlayer PlayerName) {
        statsMulti.put(PlayerName.getUniqueId(), 0);
        statsRebirth.put(PlayerName.getUniqueId(), 0);
        Button.statsYML.set("Accounts." + PlayerName.getUniqueId() + ".Multi", 0);
        Button.statsYML.set("Accounts." + PlayerName.getUniqueId() + ".Rebirth", 0);
        Button.stats.saveConfig();
        return true;
    }
    public static boolean loadAccount(OfflinePlayer PlayerName) {
        statsMulti.put(PlayerName.getUniqueId(), (Integer) Button.statsYML.get("Accounts."+PlayerName.getUniqueId()+".Multi"));
        statsRebirth.put(PlayerName.getUniqueId(), (Integer) Button.statsYML.get("Accounts."+PlayerName.getUniqueId()+".Rebirth"));
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

    public int getRebirth(OfflinePlayer PlayerName) {
        if (statsRebirth.containsKey(PlayerName.getUniqueId())) {
            return (int) statsRebirth.get(PlayerName.getUniqueId());
        }
        return 0;
    }
    public boolean addRebirth(OfflinePlayer PlayerName, Integer Amount) {
        if (statsMulti.containsKey(PlayerName.getUniqueId())) {
            int result = (int) statsRebirth.get(PlayerName.getUniqueId()) + Amount;

            statsRebirth.put(PlayerName.getUniqueId(), result);
            Button.statsYML.set("Accounts."+ PlayerName.getUniqueId()+".Rebirth", result);
            Button.stats.saveConfig();
            return true;
        }
        return false;
    }
    public boolean remRebirth(OfflinePlayer PlayerName, Integer Amount) {
        if (statsMulti.containsKey(PlayerName.getUniqueId())) {
            int result = (int) statsRebirth.get(PlayerName.getUniqueId()) - Amount;

            statsRebirth.put(PlayerName.getUniqueId(), result);
            Button.statsYML.set("Accounts."+ PlayerName.getUniqueId()+".Rebirth", result);
            Button.stats.saveConfig();
            return true;
        }
        return false;
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
