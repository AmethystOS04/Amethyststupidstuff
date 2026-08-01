package amethyst.logger;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class BeybladeCore implements ModInitializer {

    public static final String MOD_ID = "randomlogger";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private final Random random = new Random();

    @Override
    public void onInitialize() {
        LOGGER.info("beyblades yeeted");
        LOGGER.info("beyblades possibly ascended into the Aether");
        LOGGER.warn("wait-- THEY ACSENDED?!");
        LOGGER.info("AHH WATCH OUT THEY ARE COMING BACK AHHHH");
        LOGGER.error("*CLANG*");
        LOGGER.error("ow");


        }

}