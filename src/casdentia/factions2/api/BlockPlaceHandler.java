package casdentia.factions2.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceHandler implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (block.getType() == Material.BEACON) {
            Bukkit.getPluginManager().callEvent(new BeaconPlaceEvent(block));
        }
    }
}
