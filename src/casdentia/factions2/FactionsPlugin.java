package casdentia.factions2;

import casdentia.factions2.handler.BlockPlaceHandler;
import casdentia.factions2.handler.EventHandlers;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FactionsPlugin extends JavaPlugin {

    private static FactionsPlugin instance;

    public static final String DIRECTORY_PLAYERS = "plugins/Factions2/players.yml";
    public static final String DIRECTORY_FACTIONS = "plugins/Factions2/factions.yml";
    public static final String DIRECTORY_CLAIMS = "plugins/Factions2/claims.yml";

    @Override
    public void onEnable() {

        instance = this;

        getServer().getPluginManager().registerEvents(new BlockPlaceHandler(), this);
        getServer().getPluginManager().registerEvents(new EventHandlers(), this);

        getCommand("faction").setExecutor(new FactionCommand());

        getLogger().info(getName() + " enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled.");
    }

    public static FactionsPlugin getInstance() {
        return instance;
    }

    public void saveFactionsPlayer(FactionsPlayer factionsPlayer){
        File file = new File(DIRECTORY_PLAYERS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        factionsPlayer.save(configuration);
    }

    public Set<Faction> getFactions() {
        Set<Faction> factions = new HashSet<>();
        File file = new File(DIRECTORY_FACTIONS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        Set<String> keys = configuration.getKeys(false);
        for (String key : keys) {
            Faction faction = new Faction();
            faction.load(configuration.getConfigurationSection(key));
            factions.add(faction);
        }
        return factions;
    }

    public Optional<Faction> getFaction(String name) {
        Set<Faction> factions = this.getFactions();
        return factions.stream().filter(faction -> faction.getName().equalsIgnoreCase(name)).findAny();
    }

    public FactionsPlayer getFactionsPlayer(Player player) {
        String uniqueId = player.getUniqueId().toString();
        FactionsPlayer factionsPlayer = new FactionsPlayer();
        File file = new File(DIRECTORY_PLAYERS);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if (!configuration.isConfigurationSection(uniqueId)) {
            factionsPlayer = new FactionsPlayer(player, FactionRank.NONE);
        } else {
            Set<String> keys = configuration.getKeys(false);
            for (String key : keys) {
                if (!key.equals(uniqueId)) continue;
                factionsPlayer.load(configuration.getConfigurationSection(key));
            }
        }

        return factionsPlayer;
    }
}
