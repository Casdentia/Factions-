package casdentia.factions2;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;

public class FactionClaim {

    private Faction faction;

    private String worldName;
    private int chunkX, chunkZ;

    public FactionClaim(Chunk chunk, Faction faction) {
        this.faction = faction;

        worldName = chunk.getWorld().getName();
        chunkX = chunk.getX();
        chunkZ = chunk.getZ();
    }

    //FIXME May have unexpected behavior?
    /* chunkX and chunkZ may refer to block coordinates instead of chunk coordinates. */
    public Chunk getChunk() {
        return Bukkit.getWorld(worldName).getChunkAt(chunkX, chunkZ);
    }

    public Faction getFaction() {
        return faction;
    }
}
