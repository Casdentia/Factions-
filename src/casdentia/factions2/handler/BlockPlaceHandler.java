package casdentia.factions2.handler;

import casdentia.factions2.event.BeaconPlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceHandler implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.BEACON) {
            Bukkit.getPluginManager().callEvent(new BeaconPlaceEvent(block, player));
        }
    }
}
