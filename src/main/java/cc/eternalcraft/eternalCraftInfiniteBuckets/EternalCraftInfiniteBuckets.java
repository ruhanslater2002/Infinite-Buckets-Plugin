package cc.eternalcraft.eternalCraftInfiniteBuckets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public final class EternalCraftInfiniteBuckets extends JavaPlugin {

    protected static String infiniteWaterBucketName;
    protected static String infiniteLavaBucketName;
    protected static List<String> infiniteWaterBucketLore = new ArrayList<String>();
    protected static List<String> infiniteLavaBucketLore = new ArrayList<String>();

    private String playerNotOnlineMessage;
    private String commandUsageMessage;
    private String currentPlayerReceiveMessage;
    private String onlinePlayerReceiveMessage;


    @Override
    public void onDisable() {}


    @Override
    public void onEnable() {
        getCommand("ibuckets").setExecutor(this);
        getServer().getPluginManager().registerEvents(new InfiniteBucketsEvents(), this);
        // Save config
        saveDefaultConfig();

        // Get config details and set it with colors in variables to use.
        this.infiniteWaterBucketName = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Buckets.InfiniteWaterBucket.BucketName"));
        this.infiniteLavaBucketName = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Buckets.InfiniteLavaBucket.BucketName"));
        for (String bucketLoreLine : getConfig().getStringList("Buckets.InfiniteWaterBucket.BucketLore")) {
            this.infiniteWaterBucketLore.add(ChatColor.translateAlternateColorCodes('&', bucketLoreLine));
        }
        for (String bucketLoreLine : getConfig().getStringList("Buckets.InfiniteLavaBucket.BucketLore")) {
            this.infiniteLavaBucketLore.add(ChatColor.translateAlternateColorCodes('&', bucketLoreLine));
        }

        this.playerNotOnlineMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.PlayerNotOnlineMessage"));
        this.commandUsageMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.CommandUsageMessage"));
        this.currentPlayerReceiveMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.CurrentPlayerReceiveMessage"));
        this.onlinePlayerReceiveMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.OnlinePlayerReceiveMessage"));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("ibuckets")) {
            if (args.length >= 2) {

                if (args[0].equalsIgnoreCase("give") && sender.hasPermission("infinitebuckets.give")) {

                    // Water Bucket logic
                    if (args[1].equalsIgnoreCase("waterbucket")) {
                        ItemStack infiniteWaterBucket = InfiniteBuckets.createInfiniteWaterBucket();

                        // If the sender is a player and wants to give the bucket to themselves
                        if (args.length == 2 && sender instanceof Player) {
                            Player player = (Player) sender;
                            player.getInventory().addItem(infiniteWaterBucket);
                            player.sendMessage(this.currentPlayerReceiveMessage.replace("%bucket%", this.infiniteWaterBucketName));
                            return true;
                        }

                        // If sender wants to give the bucket to another player
                        else if (args.length == 3) {
                            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                if (args[2].equals(onlinePlayer.getName())) {
                                    onlinePlayer.getInventory().addItem(infiniteWaterBucket);
                                    onlinePlayer.sendMessage(this.currentPlayerReceiveMessage.replace("%bucket%", this.infiniteWaterBucketName));
                                    sender.sendMessage(this.onlinePlayerReceiveMessage.replace("%player%", onlinePlayer.getName()).replace("%bucket%", this.infiniteWaterBucketName));
                                    return true;
                                }
                            }
                            sender.sendMessage(this.playerNotOnlineMessage.replace("%player%", args[2]));
                            return false;
                        }
                    }

                    // Lava Bucket logic
                    else if (args[1].equalsIgnoreCase("lavabucket")) {
                        ItemStack infiniteLavaBucket = InfiniteBuckets.createInfiniteLavaBucket();

                        // If the sender is a player and wants to give the bucket to themselves
                        if (args.length == 2 && sender instanceof Player) {
                            Player player = (Player) sender;
                            player.getInventory().addItem(infiniteLavaBucket);
                            player.sendMessage(this.currentPlayerReceiveMessage.replace("%bucket%", this.infiniteLavaBucketName));
                            return true;
                        }

                        // If sender wants to give the bucket to another player
                        else if (args.length == 3) {
                            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                if (args[2].equals(onlinePlayer.getName())) {
                                    onlinePlayer.getInventory().addItem(infiniteLavaBucket);
                                    onlinePlayer.sendMessage(this.currentPlayerReceiveMessage.replace("%bucket%", this.infiniteLavaBucketName));
                                    sender.sendMessage(this.onlinePlayerReceiveMessage.replace("%player%", onlinePlayer.getName()).replace("%bucket%", this.infiniteLavaBucketName));
                                    return true;
                                }
                            }
                            sender.sendMessage(this.playerNotOnlineMessage.replace("%player%", args[2]));
                            return false;
                        }
                    }
                }
            }

            // If the command usage is incorrect
            sender.sendMessage(this.commandUsageMessage);
        }
        return false;
    }
}
