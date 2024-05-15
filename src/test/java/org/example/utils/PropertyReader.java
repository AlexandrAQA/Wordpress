package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final String FILE_PATH = "project.properties";
    private final Properties properties;

    public PropertyReader() {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(FILE_PATH)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUsername() {
        return properties.getProperty("ADMIN_USERNAME");
    }

    public String getPassword() {
        return properties.getProperty("ADMIN_PASSWORD");
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getBaseUrl() {
        return properties.getProperty("PROD_URL");
    }
}

