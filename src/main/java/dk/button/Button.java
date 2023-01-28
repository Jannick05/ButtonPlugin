package dk.button;

import dk.button.commands.Admin;
import dk.button.commands.Mad;
import dk.button.events.Claim;
import dk.button.events.PlayerJoin;
import dk.button.events.PlayerMove;
import dk.button.tasks.MoneyLoopTask;
import dk.button.utils.Config;

import dk.button.utils.Regions;
import dk.button.utils.Stats;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Button extends JavaPlugin {
    public static Button instance;
    public static Economy econ = null;
    private static PluginManager pluginManager;
    public static Config config, stats, regions;
    public static FileConfiguration configYML, statsYML, regionsYML;
    public Stats statss = new Stats();
    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();
        instance = this;

        getCommand("Admin").setExecutor(new Admin());
        getCommand("Mad").setExecutor(new Mad());
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new Claim(), this);

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
    public static Button getInstance(){
        return instance;
    }



}
