package de.rubingrube.SignTracker.Listeners;

import de.rubingrube.SignTracker.Loggers.SignPlacementLogger;
import de.rubingrube.SignTracker.Main;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignPlacementListener implements Listener {
    @EventHandler(priority=EventPriority.MONITOR)
    public void onSignPlacement(final SignChangeEvent b) {
        Block sign = b.getBlock();
        String line1 = b.getLine(0);
        String line2 = b.getLine(1);
        String line3 = b.getLine(2);
        String line4 = b.getLine(3);
        Boolean signIsEmpty = line1.isEmpty() && line2.isEmpty() && line3.isEmpty() && line4.isEmpty();

        if(!signIsEmpty || Main.settings.baseSettings.trackEmptySigns){
            if(!Main.settings.baseSettings.trackOnlyBlacklist || containsBlacklistedWord(line1) || containsBlacklistedWord(line2) || containsBlacklistedWord(line3) || containsBlacklistedWord(line4)){
                SignPlacementLogger.logToFile(sign, b.getPlayer(), line1, line2, line3, line4);
            }

            if(!Main.settings.baseSettings.printOnlyBlacklistToConsole || containsBlacklistedWord(line1) || containsBlacklistedWord(line2) || containsBlacklistedWord(line3) || containsBlacklistedWord(line4)){
                if(Main.settings.baseSettings.printLogToConsole){
                    if(Main.settings.baseSettings.trackEmptySigns){
                        SignPlacementLogger.logToConsole(sign, b.getPlayer(), line1, line2, line3, line4);
                    }else if(!signIsEmpty){
                        SignPlacementLogger.logToConsole(sign, b.getPlayer(), line1, line2, line3, line4);
                    }
                }
            }

            if(Main.perms != null){
                if(!Main.settings.permSettings.printOnlyBlacklistToPerm || containsBlacklistedWord(line1) || containsBlacklistedWord(line2) || containsBlacklistedWord(line3) || containsBlacklistedWord(line4)){
                    if(Main.settings.permSettings.printLogToPerm){
                        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                            if(Main.perms.has(p, "signtracker.see")) {
                                SignPlacementLogger.logToPlayer(sign, b.getPlayer(), line1, line2, line3, line4);
                            }
                        }
                    }
                }
            }
        }
    }

    public Boolean containsBlacklistedWord(String signLine){
        for(String b : Main.settings.baseSettings.blacklist){
            if(signLine.contains(b)){
                return true;
            }
        }
        return false;
    }
}
