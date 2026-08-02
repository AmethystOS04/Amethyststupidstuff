package amethyst.logger;

import amethyst.logger.media.MusicPlayer;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

import java.io.InputStream;

public class SongCommand {

    public static void register() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            dispatcher.register(

                    ClientCommands.literal("song")

                            .then(ClientCommands.literal("badapple")

                                    .executes(ctx -> {

                                        try {
                                            InputStream stream = Minecraft.getInstance()
                                                    .getResourceManager()
                                                    .getResourceOrThrow(
                                                            Identifier.fromNamespaceAndPath(
                                                                    "randomlogger",
                                                                    "music/badapple.mp3"))
                                                    .open();

                                            MusicPlayer.play(stream);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        return 1;

                                    }))

                            .then(ClientCommands.literal("bliss")

                                    .executes(ctx -> {

                                        try {
                                            InputStream stream = Minecraft.getInstance()
                                                    .getResourceManager()
                                                    .getResourceOrThrow(
                                                            Identifier.fromNamespaceAndPath(
                                                                    "randomlogger",
                                                                    "music/bliss.mp3"))
                                                    .open();

                                            MusicPlayer.play(stream);

                                            MusicPlayer.play(stream);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        return 1;

                                    }))

            );

        });

    }

}