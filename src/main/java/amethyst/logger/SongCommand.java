package amethyst.logger;

import amethyst.logger.media.MusicPlayer;
import net.minecraft.network.chat.Component;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import java.util.ServiceLoader;
import java.io.InputStream;

public class SongCommand {

    public static void register() {
        for (var reader : ServiceLoader.load(
                javax.sound.sampled.spi.AudioFileReader.class
        )) {
            System.out.println("AUDIO READER: " + reader.getClass().getName());
        }
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

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        return 1;

                                    }))


                            .then(ClientCommands.literal("everlasting_fun")

                                    .executes(ctx -> {

                                        try {
                                            InputStream stream = Minecraft.getInstance()
                                                    .getResourceManager()
                                                    .getResourceOrThrow(
                                                            Identifier.fromNamespaceAndPath(
                                                                    "randomlogger",
                                                                    "music/everlastingfun.mp3"))
                                                    .open();

                                            MusicPlayer.play(stream);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        return 1;

                                    }))

                            .then(ClientCommands.literal("the_only_thing_they_fear_is_you")

                                    .executes(ctx -> {

                                        try {
                                            InputStream stream = Minecraft.getInstance()
                                                    .getResourceManager()
                                                    .getResourceOrThrow(
                                                            Identifier.fromNamespaceAndPath(
                                                                    "randomlogger",
                                                                    "music/theonlythingtheyfearisyou.mp3"))
                                                    .open();

                                            MusicPlayer.play(stream);
                                            if (Minecraft.getInstance().player != null) {
                                                Minecraft.getInstance().player.sendSystemMessage(
                                                        Component.literal("RIP AND TEAR UNTIL IT IS DONE!!!!!!!!!!!!!!!!!!!!!")
                                                );
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        return 1;

                                    }))

                            .then(ClientCommands.literal("deep_below_the_code")

                                    .executes(ctx -> {

                                        try {
                                            InputStream stream = Minecraft.getInstance()
                                                    .getResourceManager()
                                                    .getResourceOrThrow(
                                                            Identifier.fromNamespaceAndPath(
                                                                    "randomlogger",
                                                                    "music/deepbelowthecode.mp3"))
                                                    .open();

                                            MusicPlayer.play(stream);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        return 1;

                                    }))



                            .then(ClientCommands.literal("quiet_please")

                                    .executes(ctx -> {

                                        try {
                                            InputStream stream = Minecraft.getInstance()
                                                    .getResourceManager()
                                                    .getResourceOrThrow(
                                                            Identifier.fromNamespaceAndPath(
                                                                    "randomlogger",
                                                                    "music/quietplease.mp3"))
                                                    .open();

                                            MusicPlayer.play(stream);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        return 1;

                                    }))

                            .then(ClientCommands.literal("enderman_rap")

                                    .executes(ctx -> {

                                        try {
                                            InputStream stream = Minecraft.getInstance()
                                                    .getResourceManager()
                                                    .getResourceOrThrow(
                                                            Identifier.fromNamespaceAndPath(
                                                                    "randomlogger",
                                                                    "music/endermanrap.mp3"))
                                                    .open();

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