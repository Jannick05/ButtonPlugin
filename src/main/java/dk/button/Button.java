package dk.button;

import dk.button.commands.*;
import dk.button.events.ChatListener;
import dk.button.events.Claim;
import dk.button.events.PlayerJoin;
import dk.button.events.Weather;
import dk.button.tasks.MoneyLoopTask;
import dk.button.utils.*;


import dk.button.utils.board.Board;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class Button extends JavaPlugin {
    public static Button instance;
    public final Map<UUID, Board> boards = new HashMap<>();
    public static Economy econ = null;

    private static PluginManager pluginManager;
    public static Config config, stats, regions;
    public static FileConfiguration configYML, statsYML, regionsYML;
    public Stats statsT = new Stats();
    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();
        instance = this;

        getCommand("Admin").setExecutor(new Admin());
        getCommand("Mad").setExecutor(new Mad());
        getCommand("Buy").setExecutor(new Buy());
        getCommand("SetSpawn").setExecutor(new SetSpawn());
        getCommand("Spawn").setExecutor(new Spawn());
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new Claim(), this);
        getServer().getPluginManager().registerEvents(new Weather(), this);

        //CONFIGS -------------------------------
        //Config.yml
        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);

        config = new Config(this, null, "config.yml");
        configYML = config.getConfig();

        //Regions.yml
        if (!(new File(getDataFolder(), "regions.yml")).exists())
            saveResource("regions.yml", false);

        regions = new Config(this, null, "regions.yml");
        regionsYML = regions.getConfig();

        //Stats.yml
        if (!(new File(getDataFolder(), "stats.yml")).exists())
            saveResource("stats.yml", false);

        stats = new Config(this, null, "stats.yml");
        statsYML = stats.getConfig();
        //Giver penge
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new MoneyLoopTask(), 0L, 20L);

        Regions.loadRegions();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Stats.loadAccount(player);
        }



        getServer().getScheduler().runTaskTimer(this, () -> {
            for (Board board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);

        setupEconomy();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        stats.saveConfig();
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }



    private void updateBoard(Board board) {
        board.updateTitle(Chat.colored("&a&lBUTTON &7&o("+Bukkit.getServer().getOnlinePlayers().size()+")"));
        board.updateLines(
                "",
                Chat.colored("&2&l"+board.getPlayer().getName()),
                Chat.colored("&a〡 &7Credits: &f"+Format.format(Econ.getBalance(board.getPlayer()))),
                Chat.colored("&a〡 &7Multiplier: &f"+Format.format(statsT.getMulti(board.getPlayer()))),
                Chat.colored("&a〡 &7Rebirth: &f"+Format.format(statsT.getRebirth(board.getPlayer()))),
                "",
                Chat.colored("&7button.superawesome.dk")
        );
    }



}
