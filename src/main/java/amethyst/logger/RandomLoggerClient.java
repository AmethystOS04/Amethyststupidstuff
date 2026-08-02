package amethyst.logger;

import net.fabricmc.api.ClientModInitializer;

public class RandomLoggerClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LogCommand.register();
        SongCommand.register();
    }
}