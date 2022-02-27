package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesReader() {
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("application.properties")){
            PROPERTIES.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
