package dk.button.utils;

import org.bukkit.OfflinePlayer;

import static dk.button.Button.econ;
//Shad0wsense bedre
public class Econ {

    public boolean addMoney(OfflinePlayer player, double amount) {

        return econ.depositPlayer(player, amount).transactionSuccess();
    }

    public static boolean remMoney(OfflinePlayer player, double amount) {

        return econ.withdrawPlayer(player, amount).transactionSuccess();
    }

    public static double getBalance(OfflinePlayer player) {

        return econ.getBalance(player);
    }

}
