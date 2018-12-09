package casdentia.factions2;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FactionsManager {

    public static boolean checkExistence(String name) {
        File file = new File(FileDirectory.FACTIONS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getKeys(false).stream().anyMatch(name::equalsIgnoreCase);
    }

    public static Faction getFaction(String name) {

        File file = new File(FileDirectory.FACTIONS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        Faction faction = null;
        for (String key : configuration.getKeys(false)) {
            if (key.equalsIgnoreCase(name)) {
                faction = new Faction();
                faction.load(configuration.getConfigurationSection(key));
                return faction;
            }
        }

        return faction;
    }

    public static Faction createFaction(String name, FactionsPlayer factionsPlayer) {
        Faction faction = new Faction(name, factionsPlayer);
        return faction;
    }

    public static void saveFaction(Faction faction) {
        File file = new File(FileDirectory.FACTIONS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        faction.save(configuration);

        try {
            configuration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
