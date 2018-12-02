package casdentia.factions2.api;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClaimedChunk {

    private UUID owner;
    private Chunk chunk;

    private Set<UUID> trustees = new HashSet<>();

    public ClaimedChunk(Chunk chunk, UUID owner) {
        this.chunk = chunk;
        this.owner = owner;
    }

    public ClaimedChunk(Chunk chunk, Player owner) {
        this.chunk = chunk;
        this.owner = owner.getUniqueId();
    }

    public Chunk getChunk() {
        return chunk;
    }

    public OfflinePlayer getOwner() {
        return Bukkit.getOfflinePlayer(owner);
    }

    public Set<OfflinePlayer> getTrustees() {
        return trustees.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet());
    }
}
