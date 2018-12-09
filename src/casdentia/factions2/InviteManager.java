package casdentia.factions2;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InviteManager {

    private static Set<Invitation> invitations = new HashSet<>();

    public static boolean add(Invitation invitation) {
        return invitations.add(invitation);
    }

    public static boolean remove(Invitation invitation) {
        return invitations.remove(invitation);
    }

    public static Invitation find(Player player, String factionName) {

        Optional<Invitation> invitationOptional = invitations.stream()
                .filter(invite ->invite.getInvitee().getUniqueId().equals(player.getUniqueId()))
                .filter(invite -> invite.getFaction().getName().equalsIgnoreCase(factionName))
                .findAny();

        return invitationOptional.orElse(null);
    }
}
