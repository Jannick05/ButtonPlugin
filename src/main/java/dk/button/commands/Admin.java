package dk.button.commands;

import dk.button.Button;
import dk.button.events.Claim;
import dk.button.utils.Chat;
import dk.button.utils.Stats;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Admin implements CommandExecutor {
    Button plugin;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Stats stats = new Stats();
        Player p = (Player) sender;
        if (!p.hasPermission("button.admin")) {
            sender.sendMessage(Chat.colored("&cIngen adgang"));
            return true;
        }
        String _command = (label == null) ? String.valueOf(command) : label;
        if(args.length == 0) {
            sendDefaultCommand(sender, _command);
        } else if (args[0].equalsIgnoreCase("addmulti")) {
            Integer multi = Integer.parseInt(args[2]);
            OfflinePlayer offlinePlayer = Bukkit.getPlayer(args[1]);
            sender.sendMessage(Chat.colored("&fTilføjede &c" + multi + "&f Multiplier til &a" + offlinePlayer.getName()));
            stats.addMulti(offlinePlayer, multi);
            return true;
        } else if (args[0].equalsIgnoreCase("getmulti")) {
            OfflinePlayer offlinePlayer = Bukkit.getPlayer(args[1]);
            sender.sendMessage(Chat.colored("&c" + offlinePlayer.getName() + " &fhar &c" + stats.getMutli(offlinePlayer) + "&f Multiplier"));
            return true;

        } else if (args[0].equalsIgnoreCase("delete")) {
            OfflinePlayer offlinePlayer = Bukkit.getPlayer(args[1]);
            sender.sendMessage(Chat.colored("&fSlettede &c" + offlinePlayer.getName() + "&c's &faccount"));
            stats.deleteAccount(offlinePlayer);
            return true;

        } else if (args[0].equalsIgnoreCase("getwand")) {
            p.sendMessage(Chat.colored("&fDu fik din wand"));
            p.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE, 1));
            return true;

        } else if (args[0].equalsIgnoreCase("createRegion")) {
            String regionName = args[1];


            if (Claim.adminClaim.containsKey("pos1") && Claim.adminClaim.containsKey("pos2")) {
                Location Loc1 =  Claim.adminClaim.get("pos1");
                Location Loc2 =  Claim.adminClaim.get("pos2");


                p.sendMessage(Chat.colored("&fDu har oprettet regionen &a" + regionName));
                Button.regionsYML.set("regions."+regionName+".pos1.x", Loc1.getX());
                Button.regionsYML.set("regions."+regionName+".pos1.y", Loc1.getY());
                Button.regionsYML.set("regions."+regionName+".pos1.z", Loc1.getZ());

                Button.regionsYML.set("regions."+regionName+".pos2.x", Loc2.getX());
                Button.regionsYML.set("regions."+regionName+".pos2.y", Loc2.getY());
                Button.regionsYML.set("regions."+regionName+".pos2.z", Loc2.getZ());
                Button.regionsYML.set("regions."+regionName+"price", 50);
                Button.regionsYML.set("regions."+regionName+"multiplier", 1);
                Button.regions.saveConfig();

            } else {
                p.sendMessage(Chat.colored("&fDu skal vælge nogle lokationer &c/admin getwand"));
                return true;
            }

            return true;

        } else if (args[0].equalsIgnoreCase("reload")) {
            boolean reloadSuccess;
            try {
                Button.config.reloadConfig();
                Button.configYML = Button.config.getConfig();

                reloadSuccess = true;
            } catch(Exception e){
                e.printStackTrace();
                reloadSuccess = false;
            }
            if(reloadSuccess) {
                sender.sendMessage(Chat.colored("&aReload successfully completed"));
            } else {
                sender.sendMessage(Chat.colored("&cAn error occurred. Please check the console."));
            }
            return true;
        } else {
            return false;
        }
        return false;
    }
    private void sendDefaultCommand(CommandSender sender, String command){
        String sb = "";
        sb = sb + "\n ";
        sb = sb + "&7/" + command + " reload &8» " + "&fReloader &cconfig.yml" + "\n ";
        sb = sb + "&7/" + command + " addmulti <player> <int> &8» " + "&fTilføj &cMultiplier" + "\n ";
        sb = sb + "&7/" + command + " getmulti <player> &8» " + "&fTjekker &cMultiplier" + "\n ";
        sb = sb + "&7/" + command + " delete <player> &8» " + "&fSletter &cSpiller" + "\n ";
        sb = sb + "&7/" + command + " getwand &8» " + "&fGiver dig en &cWand" + "\n ";
        sender.sendMessage(Chat.colored(sb));
    }
}
