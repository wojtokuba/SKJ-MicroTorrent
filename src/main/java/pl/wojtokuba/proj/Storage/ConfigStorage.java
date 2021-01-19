package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Utils.Filesystem;

import java.util.*;

public class ConfigStorage {
    private final HashMap<SettingValues, String> configParams = new HashMap<>();
    private final Set<String> configParamsList = new HashSet<>();

    public enum SettingValues{
        WORK_MODE,
        TRANSMISSION_PROTOCOL,
        FILES_LOCATION,
        MAX_CLIENTS,
        CLIENT_NUMBER,
    }

    public ConfigStorage(){
        //set default file storage location
        String os = System.getProperty("os.name").toLowerCase();
        byte clientNumber = (byte)(1 + (Math.random() * (100 - 1)));
        set(SettingValues.CLIENT_NUMBER, String.valueOf(clientNumber));
        set(SettingValues.MAX_CLIENTS, "300");
        if(os.contains("win")){
            set(SettingValues.FILES_LOCATION, "D:\\TORrent_"+clientNumber);
        } else {
            set(SettingValues.FILES_LOCATION, Filesystem.getUsersHomeDir()+"/TORrent_"+clientNumber);
        }
    }

    public ConfigStorage set(SettingValues key, String value) {
        this.configParams.put(key, value);
        return this;
    }

    public String get(SettingValues key) {
        try {
            return this.configParams.get(key);
        } catch (Exception e){
            return null;
        }
    }

}
