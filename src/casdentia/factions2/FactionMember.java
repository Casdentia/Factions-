package casdentia.factions2;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FactionMember implements Convertible {

    private FactionRank factionRank;

    private UUID uniqueId;

    public FactionMember() {

    }

    public FactionMember(Player player, FactionRank factionRank) {
        this.factionRank = factionRank;
        this.uniqueId = player.getUniqueId();
    }

    public FactionMember(UUID uniqueId, FactionRank factionRank) {
        this.factionRank = factionRank;
        this.uniqueId = uniqueId;
    }

    public FactionRank getFactionRank() {
        return factionRank;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer(uniqueId);
    }

    public static FactionMember getFactionMember(UUID uniqueId) {
        return Serializer.getFactionMember(uniqueId);
    }

    @Override
    public void save(ConfigurationSection section) {

        ConfigurationSection memberSection = section.createSection(uniqueId.toString());

        memberSection.set("rank", factionRank.name());
    }

    @Override
    public void load(ConfigurationSection section) {
        uniqueId = UUID.fromString(section.getName());
        factionRank = FactionRank.valueOf(section.getString("rank"));
    }
}
