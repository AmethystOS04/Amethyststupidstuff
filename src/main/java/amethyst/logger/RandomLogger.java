package amethyst.logger;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import amethyst.logger.items.TntStickItem;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import amethyst.logger.items.FireBallLauncherItem;
import amethyst.logger.items.LogStickItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
public class RandomLogger implements ModInitializer {

	public static final String MOD_ID = "randomlogger";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private final Random random = new Random();

	@Override
	public void onInitialize() {
		LogCommand.register();
		SongCommand.register();
		boolean beyblade = random.nextInt(100) == 0;

		String file = beyblade ? "beyblade.txt" : "logsnormal.txt";
        String file1 = "logs.txt";
        List<String> messages = loadMessagesStatic(file, file1);

			LOGGER.debug("Loading logs...");
		LOGGER.warn("WARNING LOGS CONTAIN TOO MUCH CHAOS");
		LOGGER.error("cooked");
			LOGGER.error("No beyblades detected :( deploying spinblades instead...");
			LOGGER.error("Spinblades deployed! sending beyblades into void...");
			LOGGER.warn("beyblades have been sent to the void");
			LOGGER.warn("FUCK IT WE B E Y B L A D E- *EXPLOSION.MP3*");
		LOGGER.warn("sulfur");
		LOGGER.warn("sulfer");
		LOGGER.warn("sulphur");

		String message = messages.get(random.nextInt(messages.size()));

		if (beyblade) {
			LOGGER.error(message);
		} else {
			LOGGER.info(message);
		}
	}

	public static List<String> loadMessagesStatic(String fileName, String file1) {

		List<String> messages = new ArrayList<>();

		try {
			InputStream stream = RandomLogger.class
					.getClassLoader()
					.getResourceAsStream(fileName);

			if (stream == null)
				return messages;

			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			String line;

			while ((line = reader.readLine()) != null) {
				if (!line.isBlank())
					messages.add(line);
			}

			reader.close();

		} catch (IOException e) {
            throw new RuntimeException(e);
        }

        return messages;
	}
	public static final Item LOG_STICK =
			Registry.register(
					BuiltInRegistries.ITEM,
					Identifier.fromNamespaceAndPath(MOD_ID, "log_stick"),
					new LogStickItem(
							new Item.Properties().setId(
									ResourceKey.create(
											Registries.ITEM,
											Identifier.fromNamespaceAndPath(MOD_ID, "log_stick")
									)
							)
					));
	public static final Block SULFUR_BLOCK =
			Registry.register(
					BuiltInRegistries.BLOCK,
					Identifier.fromNamespaceAndPath(MOD_ID, "sulfur_block"),
					new Block(
							Block.Properties.ofFullCopy(Blocks.SULFUR)
									.setId(
											ResourceKey.create(
													Registries.BLOCK,
													Identifier.fromNamespaceAndPath(MOD_ID, "sulfur_block")
											)
									)
					));
	public static final Item SULFUR_BLOCK_ITEM =
			Registry.register(
					BuiltInRegistries.ITEM,
					Identifier.fromNamespaceAndPath(MOD_ID, "sulfur_block"),
					new BlockItem(
							SULFUR_BLOCK,
							new Item.Properties().setId(
									ResourceKey.create(
											Registries.ITEM,
											Identifier.fromNamespaceAndPath(MOD_ID, "sulfur_block")
									)
							)
					));
	public static final Item FIREBALL_LAUNCHER =
			Registry.register(
					BuiltInRegistries.ITEM,
					Identifier.fromNamespaceAndPath(MOD_ID, "fireball_launcher"),
					new FireBallLauncherItem(
							new Item.Properties().setId(
									ResourceKey.create(
											Registries.ITEM,
											Identifier.fromNamespaceAndPath(MOD_ID, "fireball_launcher")
									)
							)
					));
	public static final Item TNT_STICK =
			Registry.register(
					BuiltInRegistries.ITEM,
					Identifier.fromNamespaceAndPath(MOD_ID, "tnt_stick"),
					new TntStickItem(
							new Item.Properties().setId(
									ResourceKey.create(
											Registries.ITEM,
											Identifier.fromNamespaceAndPath(MOD_ID, "tnt_stick")
									)
							)
					));
	public static final ResourceKey<CreativeModeTab> RANDOMLOGGER_TAB_KEY =
			ResourceKey.create(
					Registries.CREATIVE_MODE_TAB,
					Identifier.fromNamespaceAndPath(MOD_ID, "randomlogger")
			);

	public static final CreativeModeTab RANDOMLOGGER_TAB =
			Registry.register(
					BuiltInRegistries.CREATIVE_MODE_TAB,
					Identifier.fromNamespaceAndPath(MOD_ID, "randomlogger"),
					CreativeModeTab.builder(
									CreativeModeTab.Row.TOP,
									0
							)
							.title(Component.literal("RandomLogger"))
							.icon(() -> new ItemStack(LOG_STICK))
							.displayItems((parameters, output) -> {
								output.accept(LOG_STICK);
								output.accept(TNT_STICK);
								output.accept(FIREBALL_LAUNCHER);
								output.accept(SULFUR_BLOCK);
							})

							.build()
			);
	public static void log(String level, String message) {

		switch (level.toLowerCase()) {

			case "trace":
				LOGGER.trace(message);
				break;

			case "debug":
				LOGGER.debug(message);
				break;

			case "warn":
				LOGGER.warn(message);
				break;

			case "error":
				LOGGER.error(message);
				break;

			default:
				LOGGER.info(message);
				break;
		}

	}
	public static String getRandomMessage() {
		Random random = new Random();

		boolean beyblade = random.nextInt(100) == 0;

		
		String file = beyblade ? "beyblade.txt" : "logsnormal.txt";
		String file1 = "logs.txt";
		List<String> messages = loadMessagesStatic(file, file1);

		if (messages.isEmpty()) {
			return "No logs found";
		}

		return messages.get(random.nextInt(messages.size()));
	}
	public static List<String> loadMessages(String fileName) {

		List<String> messages = new ArrayList<>();

		try {

			InputStream stream = RandomLogger.class
					.getClassLoader()
					.getResourceAsStream(fileName);

			if (stream == null)
				return messages;

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(stream, StandardCharsets.UTF_8));

			String line;

			while ((line = reader.readLine()) != null) {

				if (!line.isBlank())
					messages.add(line);

			}

			reader.close();

		} catch (IOException e) {

			LOGGER.error("Failed to load {}", fileName, e);

		}
		return messages;



	}

}
