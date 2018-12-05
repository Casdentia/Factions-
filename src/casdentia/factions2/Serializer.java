package casdentia.factions2;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class Serializer {

    public static final String DIRECTORY_FACTIONS = "plugins/Factions2/factions.yml";
    public static final String DIRECTORY_CLAIMS = "plugins/Factions2/claims.yml";

    public static FactionMember getFactionMember(UUID uniqueId) {

        File file = new File(DIRECTORY_FACTIONS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        for (String key : configuration.getKeys(false)) {
            ConfigurationSection keySection = configuration.getConfigurationSection(key);
            ConfigurationSection memberSection = keySection.getConfigurationSection("members");
            if (!memberSection.getKeys(false).contains(uniqueId.toString())) continue;
            FactionMember factionMember = new FactionMember();
            factionMember.load(memberSection);
            return factionMember;
        }

        return null;
    }

    public static void saveFaction(Faction faction) {

        File file = new File(DIRECTORY_FACTIONS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        faction.save(configuration);

        saveConfiguration(configuration, file);
    }

    public static Faction getFaction(String name) {
        return null;
    }

    public static Faction getFactionOf(FactionMember factionMember) {

        File file = new File(DIRECTORY_FACTIONS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        for (String key : configuration.getKeys(false)) {
            ConfigurationSection keySection = configuration.getConfigurationSection(key);
            ConfigurationSection memberSection = keySection.getConfigurationSection("members");
            if (!memberSection.getKeys(false).contains(factionMember.getUniqueId().toString())) continue;
            Faction faction = new Faction();
            faction.load(keySection);
            return faction;
        }

        return null;
    }

    private static void saveConfiguration(YamlConfiguration configuration, File file) {
        try {
            configuration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
