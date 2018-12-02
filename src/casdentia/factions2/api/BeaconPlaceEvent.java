package casdentia.factions2.api;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BeaconPlaceEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    private Block beaconBlock;

    public BeaconPlaceEvent(Block beaconBlock) {
        this.beaconBlock = beaconBlock;
    }

    public Block getBeaconBlock() {
        return beaconBlock;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
