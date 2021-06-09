package net.overcrave.SignTracker;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import net.milkbowl.vault.permission.Permission;
import net.overcrave.SignTracker.Commands.BaseCmd;
import net.overcrave.SignTracker.Listeners.SignPlacementListener;
import net.overcrave.SignTracker.Settings.SettingsCore;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    public static Main I;
    public static Permission perms;
    public Toml tomlSettings;
    public File settingsFile = new File(getDataFolder(), "settings.toml");
    public static SettingsCore settings = new SettingsCore();
    public static String PluginLabel;
    public static ConsoleCommandSender console;

    @Override
    public void onLoad() {
        I = this;
        perms = null;
        console = I.getServer().getConsoleSender();
    }

    @Override
    public void onEnable() {
        saveConfig();
        PluginLabel = ChatColor.translateAlternateColorCodes('&', settings.messages.prefix);
        setupPermissions();
        getServer().getPluginManager().registerEvents(new SignPlacementListener(), I);
        I.getCommand("signtracker").setExecutor(new BaseCmd());
    }

    public static boolean setupPermissions() {
        try{
            RegisteredServiceProvider<Permission> rsp;
            rsp = I.getServer().getServicesManager().getRegistration(Permission.class);
            if(rsp != null) {
                perms = rsp.getProvider();
                return true;
            } else {
                console.sendMessage(PluginLabel + ChatColor.translateAlternateColorCodes('&', settings.messages.noPermPlugin));
                return false;
            }
        }
        catch(NoClassDefFoundError e){
            console.sendMessage(PluginLabel + ChatColor.translateAlternateColorCodes('&', settings.messages.vaultNotFound));
            return false;
        }
    }

    public void saveConfig() {
        if (!settingsFile.exists()){
            settings = new SettingsCore();
            tomlSettings = new Toml();
            settingsFile.getParentFile().mkdirs();
            try {
                settingsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            tomlSettings = new Toml().read(settingsFile);
            settings = tomlSettings.to(SettingsCore.class);
        }

        TomlWriter writer = new TomlWriter();

        try {
            writer.write(settings, settingsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetConfig(){
        if (settingsFile.exists()){
            settingsFile.delete();
        }
        saveConfig();
    }

    @Override
    public void reloadConfig() {
        saveConfig();
    }
}
