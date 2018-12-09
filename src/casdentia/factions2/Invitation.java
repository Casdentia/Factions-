package casdentia.factions2;

import org.bukkit.entity.Player;

public class Invitation {

    private Player invitee;
    private Faction faction;

    public Invitation(Player invitee, Faction faction) {
        this.invitee = invitee;
        this.faction = faction;
    }

    public Player getInvitee() {
        return invitee;
    }

    public Faction getFaction() {
        return faction;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Faction) {
           return ((Faction)obj).getName().equalsIgnoreCase(faction.getName());
        }
        return false;
    }
}
