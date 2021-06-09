package net.overcrave.SignTracker.Settings;

public class Messages {
    public String prefix;
    public String noPermPlugin;
    public String usage;
    public String configReloaded;
    public String configReset;
    public String noPerm;
    public String vaultNotFound;

    public Messages(){
        prefix = "&3[SIGNTRACKER] &r";
        usage = "&3/signtracker reload &bto reload the config - &3/signtracker resetconfig &bto reset it to the default values!";
        configReloaded = "&bConfig has been reloaded!";
        configReset = "&bConfig has been reset!";
        noPerm = "&4You do not have permission to use this command!";
        vaultNotFound = "&4Vault was not found on your server - permission based features will not work and commands will only be possible from the console!";
        noPermPlugin = "&cNo permission plugin could be found - permission based features will not work and commands will only be possible from the console!";
    }
}
