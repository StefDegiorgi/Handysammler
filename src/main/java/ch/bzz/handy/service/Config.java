package ch.bzz.handy.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * konfiguriere den Web-Service und die Eigenschaften
 */

@ApplicationPath("/resource")

public class Config extends Application {
    private static final String PROPERTIES_PATH = "/home/bzz/webapp/handymodellList.properties";
    private static Properties properties = null;

    /**
     * definiere alle Anbieterklassen
     *
     * @return set von klassen
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(HandymodellService.class);
        providers.add(HandymarkeService.class);
        providers.add(TestService.class);
        return providers;
    }

    /**
     * hole den Wert von einer Eigenschaft
     *
     * @param property den Schlüssel um die Eigenschaft zu lesen
     * @return den Wert der Eigenschaft
     */
    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return value;
    }

    /**
     * liest die Eigeschaften von den Files
     */
    private static void readProperties() {

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(inputStream);
            if (inputStream != null) inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * setzte die Eigenschaften
     *
     * @param properties den Wert zu setzten
     */
    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}