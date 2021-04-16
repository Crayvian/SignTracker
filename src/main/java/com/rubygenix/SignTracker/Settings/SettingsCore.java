package com.rubygenix.SignTracker.Settings;

public class SettingsCore {
    public BaseSettings baseSettings;
    public PermSettings permSettings;
    public Messages messages;

    public SettingsCore(){
        baseSettings = new BaseSettings();
        permSettings = new PermSettings();
        messages = new Messages();
    }
}
