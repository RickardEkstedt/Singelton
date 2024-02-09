import java.io.*;
import java.util.Properties;

public class ConfigSingleton {
    private static volatile ConfigSingleton instance;
    private Properties config;

    private ConfigSingleton() {
        config = new Properties();
        loadConfig();
    }

    public static ConfigSingleton getInstance() {
        if (instance == null) {
            synchronized (ConfigSingleton.class) {
                if (instance == null) {
                    instance = new ConfigSingleton();
                }
            }
        }
        return instance;
    }

    private void loadConfig() {
        try (InputStream input = new FileInputStream("file.cfg")) {
            config.load(input);
        } catch (FileNotFoundException e) {
            System.err.println("Config file not found. Creating a new one.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized String read(String key) {
        return config.getProperty(key);
    }

    public synchronized void save(String key, String value) {
        config.setProperty(key, value);
        try (OutputStream output = new FileOutputStream("file.cfg")) {
            config.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
