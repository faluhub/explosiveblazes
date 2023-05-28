package me.falu.explosiveblazes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import org.apache.logging.log4j.*;

public class ExplosiveBlazes implements ClientModInitializer {
    public static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer("explosiveblazes").orElseThrow(RuntimeException::new);
    public static final String MOD_NAME = MOD_CONTAINER.getMetadata().getName();
    public static final String MOD_VERSION = String.valueOf(MOD_CONTAINER.getMetadata().getVersion());
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static void log(Object msg) {
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public void onInitializeClient() {
        log("Using " + MOD_NAME + " v" + MOD_VERSION);
    }
}
