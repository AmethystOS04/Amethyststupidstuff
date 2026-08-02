package amethyst.logger;

import com.mojang.brigadier.arguments.StringArgumentType;

import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import java.util.List;
import java.util.Random;
import javazoom.jl.player.Player;
public class LogCommand {

    private static final Random RANDOM = new Random();

    public static void register() {

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