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

        if (args.length == 0) {
            //TODO Display help information.
            return true;
        } else if (args[0].equalsIgnoreCase("create")) {

            FactionMember factionMember = FactionMember.getFactionMember(player.getUniqueId());
            Faction oldFaction = Faction.getFactionOf(factionMember);
            if (oldFaction != null) {
                player.sendMessage(ChatColor.RED + "Leave your current faction before creating a new one.");
                return true;
            }

            String name = args[1];
            Faction faction = new Faction(name, player);
            Serializer.saveFaction(faction);

            player.sendMessage(ChatColor.GREEN + "Created a faction named " + faction.getName() + ".");

        } else if(args[0].equalsIgnoreCase("leave")) {

            FactionMember factionMember = FactionMember.getFactionMember(player.getUniqueId());
            Faction faction = Faction.getFactionOf(factionMember);
            faction.removeMember(factionMember);

            player.sendMessage(ChatColor.RED + "You've left your faction.");

        } else if (args[0].equalsIgnoreCase("info")) {
            FactionMember factionMember = FactionMember.getFactionMember(player.getUniqueId());
            Faction faction = Faction.getFactionOf(factionMember);
            if (faction == null) {
                player.sendMessage(ChatColor.RED + "You aren't in a faction.");
                return true;
            }
            player.sendMessage("Faction name: " + faction.getName());
            player.sendMessage("Member count: " + faction.getMembers().size());
        }

        return true;
    }
}
