package casdentia.factions2;

import org.bukkit.Chunk;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Faction implements Convertible {

    private String name;
    private Set<FactionMember> members = new HashSet<>();
    private Set<FactionClaim> factionClaims = new HashSet<>();

    public Faction() {

    }

    public Faction(String name, Player leader) {
        this.name = name;

        members.add(new FactionMember(leader, FactionRank.LEADER));
    }

    public String getName() {
        return name;
    }

    public Set<FactionMember> getMembers() {
        return members;
    }

    public Set<FactionClaim> getFactionClaims() {
        return factionClaims;
    }

    public void addClaim(Chunk chunk) {
        factionClaims.add(new FactionClaim(chunk, this));
    }

    @Override
    public void save(ConfigurationSection section) {

        //FIXME Not checking if a configuration section exists before creation may result in unexpected behavior.
        ConfigurationSection nameSection = section.createSection(name);
        ConfigurationSection membersSection = nameSection.createSection("members");

        members.forEach(member -> member.save(membersSection));
    }

    @Override
    public void load(ConfigurationSection section) {

        name = section.getName();

        ConfigurationSection membersSection = section.getConfigurationSection("members");

        for (String key : membersSection.getKeys(false)) {
            FactionMember member = new FactionMember();
            member.load(membersSection.getConfigurationSection(key));
            members.add(member);
        }
    }
}
