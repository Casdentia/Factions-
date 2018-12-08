package casdentia.factions2;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Set;
import java.util.UUID;

public class FactionsPlayer implements Convertible {

    private Player player;
    private Faction faction;
    private FactionRank rank;

    public FactionsPlayer() {

    }

    public FactionsPlayer(Player player) {
        this.player = player;
    }

    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    public Player getPlayer() {
        return player;
    }

    public void setRank(FactionRank rank) {
        this.rank = rank;
    }

    public boolean hasFaction() {
        return faction != null;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public void save() {
        Manager.save(this);
    }

    @Override
    public void save(ConfigurationSection section) {
        String uniqueId = player.getUniqueId().toString();
        section.createSection(uniqueId);
        section.set(uniqueId + ".faction", hasFaction() ? faction.getName() : "");
        section.set(uniqueId + ".rank", rank.name());
    }

    @Override
    public void load(ConfigurationSection section) {
        String factionName = section.getString("faction");
        faction = FactionsManager.getFaction(factionName);
        rank = FactionRank.valueOf(section.getString(".rank"));
    }

    /* Static methods */
    public static FactionsPlayer get(Player player) {
        return Manager.get(player);
    }

    private static class Manager {

        private static FactionsPlayer get(Player player) {

            File file = new File(FileDirectory.PLAYERS);
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            String uniqueId = player.getUniqueId().toString();
            FactionsPlayer factionsPlayer = new FactionsPlayer();

            Set<String> keys = configuration.getKeys(false);

            if (keys.stream().noneMatch(key -> key.equals(uniqueId))) {
                configuration.createSection(uniqueId);
                saveConfiguration(configuration, file);
                factionsPlayer = new FactionsPlayer(player);
                return factionsPlayer;
            }

            for (String key : keys) {
                if (key.equals(uniqueId)) {
                    ConfigurationSection playerSection = configuration.getConfigurationSection(key);
                    factionsPlayer.load(playerSection);
                }
            }

            return factionsPlayer;
        }

        private static void save(FactionsPlayer factionsPlayer) {
            File file = new File(FileDirectory.PLAYERS);
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            factionsPlayer.save(configuration);
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
}
