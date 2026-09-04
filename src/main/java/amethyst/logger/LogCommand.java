package amethyst.logger;

import com.mojang.brigadier.arguments.StringArgumentType;

import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javazoom.jl.player.Player;
public class LogCommand {
    private static List<String> everlastingFunLogs = List.of();
    private static int everlastingFunIndex = 0;
    private static int everlastingFunTimer = 0;
    private static boolean everlastingFunPlaying = false;
    private static final Random RANDOM = new Random();
    private static void playEverlastingFun() {
        everlastingFunLogs =
                RandomLogger.loadMessages("everlasting_fun.txt");

        if (everlastingFunLogs.isEmpty()) {
            return;
        }

        everlastingFunIndex = 0;
        everlastingFunTimer = 0;
        everlastingFunPlaying = true;
    }
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (!everlastingFunPlaying) {
                return;
            }

            if (everlastingFunTimer > 0) {
                everlastingFunTimer--;
                return;
            }

            if (everlastingFunIndex >= everlastingFunLogs.size()) {
                everlastingFunPlaying = false;
                return;
            }

            RandomLogger.log(
                    "info",
                    everlastingFunLogs.get(everlastingFunIndex)
            );

            everlastingFunIndex++;

            everlastingFunTimer = 20;
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {


            dispatcher.register(

                    ClientCommands.literal("log")

                            // /log info <message>
                            .then(ClientCommands.literal("info")
                                    .then(ClientCommands.argument(
                                                    "message",
                                                    StringArgumentType.greedyString())

                                            .executes(ctx -> {

                                                RandomLogger.log(
                                                        "info",
                                                        StringArgumentType.getString(
                                                                ctx,
                                                                "message"));

                                                return 1;
                                            })))

                            // /log warn <message>
                            .then(ClientCommands.literal("warn")
                                    .then(ClientCommands.argument(
                                                    "message",
                                                    StringArgumentType.greedyString())

                                            .executes(ctx -> {

                                                RandomLogger.log(
                                                        "warn",
                                                        StringArgumentType.getString(
                                                                ctx,
                                                                "message"));

                                                return 1;
                                            })))

                            // /log error <message>
                            .then(ClientCommands.literal("error")
                                    .then(ClientCommands.argument(
                                                    "message",
                                                    StringArgumentType.greedyString())

                                            .executes(ctx -> {

                                                RandomLogger.log(
                                                        "error",
                                                        StringArgumentType.getString(
                                                                ctx,
                                                                "message"));

                                                return 1;
                                            })))

                            // /log debug <message>
                            .then(ClientCommands.literal("debug")
                                    .then(ClientCommands.argument(
                                                    "message",
                                                    StringArgumentType.greedyString())

                                            .executes(ctx -> {

                                                RandomLogger.log(
                                                        "debug",
                                                        StringArgumentType.getString(
                                                                ctx,
                                                                "message"));

                                                return 1;
                                            })))

                            // /log trace <message>
                            .then(ClientCommands.literal("trace")
                                    .then(ClientCommands.argument(
                                                    "message",
                                                    StringArgumentType.greedyString())

                                            .executes(ctx -> {

                                                RandomLogger.log(
                                                        "trace",
                                                        StringArgumentType.getString(
                                                                ctx,
                                                                "message"));

                                                return 1;
                                            })))

                            // /log random
                            .then(ClientCommands.literal("random")

                                    .executes(ctx -> {

                                        List<String> logs =
                                                RandomLogger.loadMessages(
                                                        "logsnormal.txt");

                                        if (!logs.isEmpty()) {

                                            RandomLogger.log(
                                                    "info",
                                                    logs.get(
                                                            RANDOM.nextInt(
                                                                    logs.size())));
                                        }

                                        return 1;
                                    }))
                            .then(ClientCommands.literal("everlasting_fun")
                                    .executes(ctx -> {

                                        playEverlastingFun();

                                        return 1;
                                    }))
                            // /log beyblade
                            .then(ClientCommands.literal("beyblade")

                                    .executes(ctx -> {

                                        List<String> logs =
                                                RandomLogger.loadMessages(
                                                        "beyblade.txt");

                                        if (!logs.isEmpty()) {

                                            RandomLogger.log(
                                                    "error",
                                                    logs.get(
                                                            RANDOM.nextInt(
                                                                    logs.size())));
                                        }

                                        return 1;
                                    }))

            );

        });

    }

}