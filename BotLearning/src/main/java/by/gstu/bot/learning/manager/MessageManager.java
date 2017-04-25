package by.gstu.bot.learning.manager;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static ResourceBundle bundle;

    public MessageManager() {
        bundle = ResourceBundle.getBundle("properties.resfile");
    }

    public MessageManager(Locale locale) {
        bundle = ResourceBundle.getBundle("properties.resfile", locale);
    }

    public String getObject(String key) {
        String value = null;
        try {
            value = new String(bundle.getString(key).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            value = bundle.getString(key);
        } finally {
            return value;
        }
    }
}
