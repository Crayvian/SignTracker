package de.rubingrube.SignTracker.Settings;

import java.util.Arrays;
import java.util.List;

public class BaseSettings {
    public boolean trackEmptySigns;
    public boolean printLogToConsole;
    public boolean trackOnlyBlacklist;
    public boolean printOnlyBlacklistToConsole;
    public List<String> blacklist;

    public BaseSettings(){
        trackEmptySigns = false;
        printLogToConsole = true;
        trackOnlyBlacklist = false;
        printOnlyBlacklistToConsole = false;
        blacklist = Arrays.asList("test","test2","test three");
    }
}
