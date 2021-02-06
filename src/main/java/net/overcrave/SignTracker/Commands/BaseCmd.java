package net.overcrave.SignTracker.Commands;

import net.overcrave.SignTracker.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length > 0){
            switch(args[0]){
                case "reload":
                    if(Main.settings.permSettings.printLogToPerm && sender instanceof Player){
                        if(Main.perms == null){
                            sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.noPermPlugin));
                            return true;
                        }else if(Main.perms.has(sender, "signtracker.reload")){
                            Main.I.reloadConfig();
                            sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.configReloaded));
                            return true;
                        }else{
                            sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.noPerm));
                            return true;
                        }
                    }else{
                        Main.I.reloadConfig();
                        sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.configReloaded));
                        return true;
                    }
                case "resetconfig":
                    if(Main.settings.permSettings.printLogToPerm && sender instanceof Player){
                        if(Main.perms == null){
                            sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.noPermPlugin));
                            return true;
                        }else if(Main.perms.has(sender, "signtracker.reset")){
                            Main.I.resetConfig();
                            sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.configReset));
                            return true;
                        }else{
                            sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.noPerm));
                            return true;
                        }
                    }else{
                        Main.I.resetConfig();
                        sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.configReset));
                        return true;
                    }
                default:
                    sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.usage));
                    return true;
            }
        }else{
            sender.sendMessage(Main.PluginLabel + ChatColor.translateAlternateColorCodes('&', Main.settings.messages.usage));
            return true;
        }
    }
}