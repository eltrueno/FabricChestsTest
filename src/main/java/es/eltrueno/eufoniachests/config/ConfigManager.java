package es.eltrueno.eufoniachests.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.eltrueno.eufoniachests.Eufoniachests;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    //private static final Type TYPE = new TypeToken<Config>() {}.getType();
    private static final Path CONFIG_FILE = Paths.get("config/"+ Eufoniachests.MOD_ID +"/config.json");


    public static Config loadConfig() throws IOException {
        if (Files.exists(CONFIG_FILE)) {
            String json = Files.readString(CONFIG_FILE);
            return GSON.fromJson(json, Config.class);
        } else {
            Config defaultCfg = new Config();
            saveConfig(defaultCfg);
            return defaultCfg;
        }
    }

    public static void saveConfig(Config cfg) throws IOException {
        String jsonData = GSON.toJson(cfg, Config.class);
        Files.createDirectories(CONFIG_FILE.getParent());
        Files.writeString(CONFIG_FILE, jsonData);
    }
}
