package casdentia.factions2.handler;

import casdentia.factions2.event.BeaconPlaceEvent;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EventHandlers implements Listener {

    @EventHandler
    public void onBeaconPlace(BeaconPlaceEvent event) {

        Block beaconBlock = event.getBeaconBlock();
    }
}
