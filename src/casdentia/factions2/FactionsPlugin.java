package casdentia.factions2;

import casdentia.factions2.handler.BlockPlaceHandler;
import casdentia.factions2.handler.EventHandlers;
import org.bukkit.plugin.java.JavaPlugin;

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
}
