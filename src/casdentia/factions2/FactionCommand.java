package casdentia.factions2;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        FactionsPlugin plugin = FactionsPlugin.getInstance();

        if (args.length == 0) {
            //TODO Display help information.
            return true;
        } else if (args[0].equalsIgnoreCase("create")) {

            if (plugin.getFactionsPlayer(player).hasFaction()) {
                player.sendMessage(ChatColor.RED + "Leave your current faction before creating a new one.");
                return true;
            }

            String name = args[1];
            Faction faction = new Faction(name);

            player.sendMessage(ChatColor.GREEN + "Created a faction named " + faction.getName() + ".");

        } else if (args[0].equalsIgnoreCase("leave")) {

            FactionsPlayer factionsPlayer = FactionsPlugin.getInstance().getFactionsPlayer(player);
            if (factionsPlayer.hasFaction()) {
                factionsPlayer.setFaction(null);
                return true;
            }

            player.sendMessage(ChatColor.RED + "You aren't in a faction.");

        } else if (args[0].equalsIgnoreCase("info")) {
        }

        return true;
    }
}
