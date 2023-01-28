package dk.button.utils;

import dk.button.utils.Cuboid;
import dk.button.Button;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;


public class Regions {

    public static Map<String, Cuboid> regions = new HashMap<>();

    public static void loadRegions() {
        System.out.println(Button.regionsYML.getConfigurationSection("regions").getValues(false));

        ConfigurationSection regionSection = Button.regionsYML.getConfigurationSection("regions");
        if (regionSection != null) {
            for (String regionData : regionSection.getKeys(false)) {
                ConfigurationSection regionConfig = regionSection.getConfigurationSection(regionData);
                int x1 = regionConfig.getInt("pos1.x");
                int y1 = regionConfig.getInt("pos1.y");
                int z1 = regionConfig.getInt("pos1.z");
                int x2 = regionConfig.getInt("pos2.x");
                int y2 = regionConfig.getInt("pos2.y");
                int z2 = regionConfig.getInt("pos2.z");
                Location loc1 = new Location(Bukkit.getWorld("World"), x1, y2, z1);
                Location loc2 = new Location(Bukkit.getWorld("World"), x2, y1, z2);
                Cuboid cuboid = new Cuboid(loc1, loc2);
                regions.put(regionData, cuboid);
            }
        }
    }
}
