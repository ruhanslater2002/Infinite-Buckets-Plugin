package cc.eternalcraft.eternalCraftInfiniteBuckets;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class InfiniteBuckets {


    // Creates infinite water bucket
    public static ItemStack createInfiniteWaterBucket() {
        // Create a water bucket item with custom display name
        ItemStack waterBucket = new ItemStack(Material.WATER_BUCKET);
        ItemMeta meta = waterBucket.getItemMeta();

        if (meta != null) {
            // Set display name
            meta.setDisplayName(EternalCraftInfiniteBuckets.infiniteWaterBucketName);

            // Set Lore
            meta.setLore(EternalCraftInfiniteBuckets.infiniteWaterBucketLore);

            // Apply the meta to the item
            waterBucket.setItemMeta(meta);
        }
        return waterBucket;
    }


    // Creates infinite lava bucket
    public static ItemStack createInfiniteLavaBucket() {
        ItemStack lavaBucket = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta meta = lavaBucket.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(EternalCraftInfiniteBuckets.infiniteLavaBucketName);
            meta.setLore(EternalCraftInfiniteBuckets.infiniteLavaBucketLore);
            lavaBucket.setItemMeta(meta);
        }
        return lavaBucket;
    }
}
