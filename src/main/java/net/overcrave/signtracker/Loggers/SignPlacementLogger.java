package net.overcrave.signtracker.Loggers;

import net.overcrave.signtracker.Main;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignPlacementLogger {
    private static DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalDateTime now = LocalDateTime.now();

    private static void logToFile(String message) {
        try {
            File dataFolder = Main.I.getDataFolder();
            if(!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File saveTo = new File(Main.I.getDataFolder(), "trackedSigns.yml");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }

            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(message);
            pw.flush();
            pw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void logToFile(Block sign, Player player, String line1, String line2, String line3, String line4){
        logToFile(date.format(now) + ":");
        SignPlacementLogger.logToFile("  Time: " + time.format(now));
        SignPlacementLogger.logToFile("    WrittenBy: " + player.getDisplayName());
        SignPlacementLogger.logToFile("    World: " + sign.getWorld().getName());
        SignPlacementLogger.logToFile("      Coordinates: " + sign.getX() + ", " + sign.getY() + ", " + sign.getZ() + "");
        SignPlacementLogger.logToFile("    Lines:");
        SignPlacementLogger.logToFile("      1: " + line1);
        SignPlacementLogger.logToFile("      2: " + line2);
        SignPlacementLogger.logToFile("      3: " + line3);
        SignPlacementLogger.logToFile("      4: " + line4);
        SignPlacementLogger.logToFile("");
    }

    public static void logToConsole(Block sign, Player player, String line1, String line2, String line3, String line4){
        Main.console.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + " has written a sign at " + sign.getWorld().getName() + ", " + sign.getX() + ", " + sign.getY() + ", " + sign.getZ());
        Main.console.sendMessage(ChatColor.AQUA + "1: " + line1);
        Main.console.sendMessage(ChatColor.AQUA + "2: " + line2);
        Main.console.sendMessage(ChatColor.AQUA + "3: " + line3);
        Main.console.sendMessage(ChatColor.AQUA + "4: " + line4);
    }

    public static void logToPlayer(Block sign, Player player, String line1, String line2, String line3, String line4){
        player.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + " has written a sign at " + sign.getWorld().getName() + ", " + sign.getX() + ", " + sign.getY() + ", " + sign.getZ());
        player.sendMessage(ChatColor.AQUA + "1: " + line1);
        player.sendMessage(ChatColor.AQUA + "2: " + line2);
        player.sendMessage(ChatColor.AQUA + "3: " + line3);
        player.sendMessage(ChatColor.AQUA + "4: " + line4);
    }
}
