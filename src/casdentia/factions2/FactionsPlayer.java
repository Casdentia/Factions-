package casdentia.factions2;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class FactionsPlayer implements Convertible {

    private Faction faction;
    private FactionRank factionRank;

    private UUID uniqueId;

    public FactionsPlayer() {

    }

    public FactionsPlayer(Player player, FactionRank factionRank) {
        this.factionRank = factionRank;
        this.uniqueId = player.getUniqueId();
    }

    public FactionsPlayer(UUID uniqueId, FactionRank factionRank) {
        this.factionRank = factionRank;
        this.uniqueId = uniqueId;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public boolean hasFaction() {
        return faction != null;
    }

    public Faction getFaction() {
        return faction;
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

    @Override
    public void save(ConfigurationSection section) {

        ConfigurationSection playerSection = section.createSection(uniqueId.toString());

        playerSection.set("faction", faction);
        playerSection.set("rank", factionRank.name());
    }

    @Override
    public void load(ConfigurationSection section) {
        uniqueId = UUID.fromString(section.getName());
        FactionsPlugin plugin = FactionsPlugin.getInstance();
        Optional<Faction> factionOptional = plugin.getFaction(section.getString("faction"));
        faction = factionOptional.orElse(null);
        factionRank = FactionRank.valueOf(section.getString("rank"));
    }
}
