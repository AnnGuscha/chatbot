package by.gstu.bot.learning.manager;

import java.util.ResourceBundle;

/**
 * Created by Anna on 12/1/2015.
 */
public class ConfigManager {

    private static ConfigManager ourInstance;
    private static ResourceBundle rb;

    private ConfigManager() {
        rb = ResourceBundle.getBundle("properties.mySqlConf");
    }

    public static ConfigManager getInstance() {
        if (ourInstance == null)
            ourInstance = new ConfigManager();
        return ourInstance;
    }

    public String getObject(String key) {
        return rb.getString(key);
    }
}