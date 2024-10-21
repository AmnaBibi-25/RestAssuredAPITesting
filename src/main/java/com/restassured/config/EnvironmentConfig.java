package com.restassured.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class EnvironmentConfig {

    private static Config config;

    public static void loadEnvironment(String env) {
        Config allConfig = ConfigFactory.parseResources("env.conf");
        config = allConfig.getConfig("environments").getConfig(env);
    }

    public static String get(String key) {
        return config.getString(key);
    }
}
