package casdentia.factions2.claim;

import casdentia.factions2.api.BeaconPlaceEvent;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EventHandlers implements Listener {

    @EventHandler
    public void onBeaconPlace(BeaconPlaceEvent event) {

        Block beacon = event.getBeaconBlock();

        System.out.println("Location: " + beacon.getLocation() + ".");
    }
}
