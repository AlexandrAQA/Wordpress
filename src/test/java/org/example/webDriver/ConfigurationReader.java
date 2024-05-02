package org.example.webDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    public static String getProperty(String key) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("project.properties"); // Update the path to the actual location of your config.properties file
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
