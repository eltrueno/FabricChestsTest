package es.eltrueno.eufoniachests;

import es.eltrueno.eufoniachests.command.ChestLoot;
import es.eltrueno.eufoniachests.config.Config;
import es.eltrueno.eufoniachests.config.ConfigManager;
import es.eltrueno.eufoniachests.event.PlayerEventsListener;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Eufoniachests implements ModInitializer {

    public static final String MOD_ID = "eufoniachests";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    private static Config config;

    @Override
    public void onInitialize() {
        LOGGER.info("Iniciando mod:"+MOD_ID);

        /*Registro de eventos*/
        LOGGER.info("["+MOD_ID+"] Registrando eventos");
        UseBlockCallback.EVENT.register(new PlayerEventsListener()::useChest);

        LOGGER.info("["+MOD_ID+"] Registrando comandos");
        /*Registramos comando(s)*/
        ChestLoot.registerCommand();

        /*Cargamos config*/
        LOGGER.info("["+MOD_ID+"] Cargando configuración");
        try {
            loadConfig();
        } catch (IOException e) {
            LOGGER.error("Ha ocurrido un error cargando la configuración");
            e.printStackTrace();
        }
    }

    public static void loadConfig() throws IOException {
        config = ConfigManager.loadConfig();
    }

    public static Config getConfig() {
        return config;
    }
}