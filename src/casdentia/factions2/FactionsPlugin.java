package casdentia.factions2;

import casdentia.factions2.handler.BlockPlaceHandler;
import casdentia.factions2.handler.EventHandlers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class FactionsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new BlockPlaceHandler(), this);
        getServer().getPluginManager().registerEvents(new EventHandlers(), this);

        getCommand("faction").setExecutor(new FactionCommand());

        getLogger().info(getName() + " enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        return true;
    }
}
