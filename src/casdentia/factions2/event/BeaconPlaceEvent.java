package casdentia.factions2.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BeaconPlaceEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    private Block beaconBlock;
    private Player player;

    public BeaconPlaceEvent(Block beaconBlock, Player player) {
        this.beaconBlock = beaconBlock;
        this.player = player;
    }

    public Block getBeaconBlock() {
        return beaconBlock;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
