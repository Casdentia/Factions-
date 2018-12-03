package casdentia.factions2;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Serializer {

    public static final String DIRECTORY_FACTIONS = "plugins/Factions2/factions.yml";
    public static final String DIRECTORY_CLAIMS = "plugins/Factions2/claims.yml";

    public static void saveFaction(Faction faction) {

        File file = new File(DIRECTORY_FACTIONS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        faction.save(configuration);

        saveConfiguration(configuration, file);
    }

    private static void saveConfiguration(YamlConfiguration configuration, File file) {
        try {
            configuration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
