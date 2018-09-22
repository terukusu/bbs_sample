package org.terukusu.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Config {

    private static final String CONFIG_PROPERTIES_NAME = "config.properties";

    private static final Map<String, String> config = new ConcurrentHashMap<>();

    protected synchronized static void init() {
        if (!config.isEmpty()) {
            Log.warn("Config is already initialized. can't initialize multiple times.");
            return;
        }

        Properties prop = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream is = classLoader.getResourceAsStream(CONFIG_PROPERTIES_NAME)) {
            prop.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> propMap = prop.entrySet().stream()
                .collect(Collectors.toMap(e -> (String) e.getKey(), e -> (String) e.getValue()));

        config.putAll(propMap);
    }

    public static String get(String key) {
        if (config.isEmpty()) {
            init();
        }
        return config.get(key);
    }

    public static void set(String key, String value) {
        config.put(key, value);
    }
}
