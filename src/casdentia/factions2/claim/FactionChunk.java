package casdentia.factions2.claim;

import casdentia.factions2.api.ClaimedChunk;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FactionChunk extends ClaimedChunk {

    private Block beaconBlock;

    public FactionChunk(Chunk chunk, Block beaconBlock, UUID owner) {
        super(chunk, owner);
        this.beaconBlock = beaconBlock;
    }

    public FactionChunk(Chunk chunk, Block beaconBlock, Player owner) {
        super(chunk, owner);
        this.beaconBlock = beaconBlock;
    }

    public Block getBeaconBlock() {
        return beaconBlock;
    }
}
