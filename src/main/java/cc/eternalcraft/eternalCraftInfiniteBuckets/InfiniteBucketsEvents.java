package cc.eternalcraft.eternalCraftInfiniteBuckets;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InfiniteBucketsEvents implements Listener {

    @EventHandler
    public void onInfiniteBucketPlacement(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInHand();

            if (item != null) {
                if (item.getType() == Material.WATER_BUCKET) {
                    ItemMeta itemMeta = item.getItemMeta();
                    // Check if lore and name matches
                    if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equals(EternalCraftInfiniteBuckets.infiniteWaterBucketName) && itemMeta.getLore().equals(EternalCraftInfiniteBuckets.infiniteWaterBucketLore)) {
                        event.setCancelled(true);
                        event.getClickedBlock().getRelative(event.getBlockFace()).setType(Material.WATER);
                        player.setItemInHand(InfiniteBuckets.createInfiniteWaterBucket());
                    }

                } else if (item.getType() == Material.LAVA_BUCKET) {
                    ItemMeta itemMeta = item.getItemMeta();
                    // Check if lore and name matches
                    if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equals(EternalCraftInfiniteBuckets.infiniteLavaBucketName) && itemMeta.getLore().equals(EternalCraftInfiniteBuckets.infiniteLavaBucketLore)) {
                        event.setCancelled(true);
                        event.getClickedBlock().getRelative(event.getBlockFace()).setType(Material.LAVA);
                        player.setItemInHand(InfiniteBuckets.createInfiniteLavaBucket());
                    }
                }
            }
        }
    }
}
