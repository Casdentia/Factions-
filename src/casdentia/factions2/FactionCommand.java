package casdentia.factions2;

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

        if (args.length == 0) {
            //TODO Display help information.
            return true;
        } else if (args[0].equalsIgnoreCase("create")) {

            String name = args[1];

            Faction faction = new Faction(name, player);

            Serializer.saveFaction(faction);
        }

        return true;
    }
}
