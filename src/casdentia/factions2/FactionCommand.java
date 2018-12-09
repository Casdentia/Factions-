package casdentia.factions2;

import org.bukkit.Bukkit;
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
            //TODO Display help information
        }

        if (args[0].equalsIgnoreCase("create")) {

            FactionsPlayer factionsPlayer = FactionsPlayer.get(player);
            if (factionsPlayer.hasFaction()) {
                player.sendMessage(ChatColor.RED + "You are already in a faction.");
                return true;
            }

            String name = args[1];
            if (FactionsManager.checkExistence(name)) {
                player.sendMessage(ChatColor.RED + "A faction with that name already exists.");
                return true;
            }

            Faction faction = FactionsManager.createFaction(name, factionsPlayer);
            factionsPlayer.setFaction(faction);
            factionsPlayer.save();
            FactionsManager.saveFaction(faction);


            player.sendMessage(ChatColor.GREEN + "Created a faction named " + faction.getName() + ".");
        }

        if (args[0].equalsIgnoreCase("leave")) {

            FactionsPlayer factionsPlayer = FactionsPlayer.get(player);
            if (!factionsPlayer.hasFaction()) {
                player.sendMessage(ChatColor.RED + "You aren't in a faction.");
                return true;
            }

            factionsPlayer.setFaction(null);
            factionsPlayer.save();

            player.sendMessage(ChatColor.YELLOW + "You have left your faction.");
        }

        if (args[0].equalsIgnoreCase("join")) {

            String name = args[1];

            Invitation invitation = InviteManager.find(player, name);
            if (invitation == null) {
                player.sendMessage("You don't have any pending invitations for that faction.");
                return true;
            }

            FactionsPlayer factionsPlayer = FactionsPlayer.get(player);

            Faction faction = invitation.getFaction();
            factionsPlayer.setFaction(faction);
            factionsPlayer.save();

            InviteManager.remove(invitation);

            player.sendMessage(ChatColor.GREEN + "You have joined " + faction.getName() + ".");
        }

        if (args[0].equalsIgnoreCase("invite")) {

            FactionsPlayer factionsPlayer = FactionsPlayer.get(player);
            if (!factionsPlayer.hasFaction()) {
                player.sendMessage(ChatColor.RED + "You are not in a faction.");
                return true;
            }

            if (factionsPlayer.getRank().getValue() < FactionRank.MODERATOR.getValue()) {
                player.sendMessage(ChatColor.RED + "You must be a moderator or higher to invite others.");
                return true;
            }

            String name = args[1];
            Player invitee = Bukkit.getPlayer(name);
            if (invitee == null) {
                player.sendMessage(ChatColor.RED + "Could not find player with name " + args[0] + ".");
                return true;
            }

            Invitation invitation = new Invitation(invitee, factionsPlayer.getFaction());
            if (InviteManager.add(invitation)) {
                invitee.sendMessage(ChatColor.YELLOW + player.getName() + " invited you to join their faction.");
                player.sendMessage(ChatColor.GREEN + "Invitation sent.");
            } else {
                player.sendMessage(ChatColor.RED + "An invitation has already been sent to this player.");
            }
        }

        return true;
    }
}
