package amethyst.logger;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import amethyst.logger.items.TheVoidsEdgeItem;
import amethyst.logger.items.CorruptedBladeItem;
import amethyst.logger.items.LightningBombItem;
import amethyst.logger.items.TheEndCrystalsBeamItem;
import amethyst.logger.items.TheRazorOfTheEndItem;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import amethyst.logger.items.TheEndsEndItem;
import amethyst.logger.items.WindWandItem;
import amethyst.logger.items.RazorOfHellItem;
import amethyst.logger.items.RazorOfSmolItem;
import amethyst.logger.items.TntStickItem;
import amethyst.logger.items.LogGernadeItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.network.chat.Component;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.CheckedOutputStream;

import amethyst.logger.items.FireBallLauncherItem;
import amethyst.logger.items.LogStickItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
public class RandomLogger implements ModInitializer {

	public static final String MOD_ID = "randomlogger";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private final Random random = new Random();

	@Override
	public void onInitialize() {
		boolean beyblade = random.nextInt(100) == 0;

		String file = beyblade ? "beyblade.txt" : "logsnormal.txt";
		String file1 = "logs.txt";
		List<String> messages = loadMessagesStatic(file, file1);


		LOGGER.debug("Loading logs...");
		LOGGER.info("why tf is the wind wand from the craftmine update here");
		LOGGER.warn("WARNING LOGS CONTAIN TOO MUCH CHAOS");
		LOGGER.error("cooked");
		LOGGER.error("No beyblades detected :( deploying spinblades instead...");
		LOGGER.error("Spinblades deployed! sending beyblades into void...");
		LOGGER.warn("beyblades have been sent to the void");
		LOGGER.warn("FUCK IT WE B E Y B L A D E- *EXPLOSION.MP3*");
		LOGGER.warn("sulfur");
		LOGGER.warn("sufur");
		LOGGER.warn("suflur");
		LOGGER.warn("sulfur my beloved");
		LOGGER.warn("sulfur shall rule the world >:3333");
		LOGGER.warn("sulfer");
		LOGGER.warn("sulphur");
		LOGGER.warn("2.1.4 backport to 1.21.11 coming never >:3");
		LOGGER.error("sorry those who are on 1.21.11 youre stuck with 2.0 :p");

		if (!messages.isEmpty()) {
			String message = messages.get(random.nextInt(messages.size()));

			if (beyblade) {
				LOGGER.error(message);
			} else {
				LOGGER.info(message);
			}
		}

		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if (source.isBuiltin() && key.equals(BuiltInLootTables.END_CITY_TREASURE)) {
				tableBuilder.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.when(LootItemRandomChanceCondition.randomChance(0.24F))
								.add(LootItem.lootTableItem(THE_ENDS_END))
				);
				tableBuilder.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.when(LootItemRandomChanceCondition.randomChance(0.07F))
								.add(LootItem.lootTableItem(THE_RAZOR_OF_THE_END))
				);
				tableBuilder.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.when(LootItemRandomChanceCondition.randomChance(0.04F))
								.add(LootItem.lootTableItem(THE_VOIDS_EDGE))
				);
				tableBuilder.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.when(LootItemRandomChanceCondition.randomChance(0.29F))
								.add(LootItem.lootTableItem(CORRUPTED_BLADE))
				);
			}
		});
	}

	public static List<String> loadMessagesStatic(String fileName, String file1) {

		List<String> messages = new ArrayList<>();

		InputStream stream = RandomLogger.class
				.getClassLoader()
				.getResourceAsStream(fileName);

		if (stream == null)
			return messages;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

			String line;

			while ((line = reader.readLine()) != null) {
				if (!line.isBlank())
					messages.add(line);
			}

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
	public static final Item WIND_WAND = Registry.register(
			BuiltInRegistries.ITEM,
			Identifier.fromNamespaceAndPath(MOD_ID, "wind_wand"),
			new WindWandItem(
					new Item.Properties()
							.setId(ResourceKey.create(
									Registries.ITEM,
									Identifier.fromNamespaceAndPath(MOD_ID, "wind_wand")
							))
			)
	);
	public static final Item LIGHTNING_BOMB =
			Registry.register(
					BuiltInRegistries.ITEM,
					Identifier.fromNamespaceAndPath(MOD_ID, "lightning_bomb"),
					new LightningBombItem(
							new Item.Properties().setId(
									ResourceKey.create(
											Registries.ITEM,
											Identifier.fromNamespaceAndPath(MOD_ID, "lightning_bomb")
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
	public static final Item THE_ENDS_END = Registry.register(
			BuiltInRegistries.ITEM,
			Identifier.fromNamespaceAndPath(MOD_ID, "the_ends_end"),
			new TheEndsEndItem(
					new Item.Properties()
							.sword(ToolMaterial.NETHERITE, 10.0F, 4.7F)
							.durability(6300)
							.setId(ResourceKey.create(
									Registries.ITEM,
									Identifier.fromNamespaceAndPath(MOD_ID, "the_ends_end")
							))
			)
	);
	public static final Item THE_END_CRYSTALS_BEAM = Registry.register(
			BuiltInRegistries.ITEM,
			Identifier.fromNamespaceAndPath(MOD_ID, "the_end_crystals_beam"),
			new TheEndCrystalsBeamItem(
					new Item.Properties()
							.sword(ToolMaterial.NETHERITE, 40.0F, 1.7F)
							.durability(6307770)
							.setId(ResourceKey.create(
									Registries.ITEM,
									Identifier.fromNamespaceAndPath(MOD_ID, "the_end_crystals_beam")
							))
			)
	);
	public static final Item RAZOR_OF_HELL = Registry.register(
			BuiltInRegistries.ITEM,
			Identifier.fromNamespaceAndPath(MOD_ID, "razor_of_hell"),
			new RazorOfHellItem(
					new Item.Properties()
							.sword(ToolMaterial.NETHERITE, 24.0F, 88.7F)
							.durability(677300)
							.setId(ResourceKey.create(
									Registries.ITEM,
									Identifier.fromNamespaceAndPath(MOD_ID, "razor_of_hell")
							))
			)
	);
	public static final Item RAZOR_OF_SMOL = Registry.register(
			BuiltInRegistries.ITEM,
			Identifier.fromNamespaceAndPath(MOD_ID, "razor_of_smol"),
			new RazorOfSmolItem(
					new Item.Properties()
							.sword(ToolMaterial.NETHERITE, 2.0F, 885.7F)
							.durability(6773050)
							.setId(ResourceKey.create(
									Registries.ITEM,
									Identifier.fromNamespaceAndPath(MOD_ID, "razor_of_smol")
							))
			)
	);
	public static final Item THE_RAZOR_OF_THE_END = Registry.register(
			BuiltInRegistries.ITEM,
			Identifier.fromNamespaceAndPath(MOD_ID, "the_razor_of_the_end"),
			new TheRazorOfTheEndItem(
					new Item.Properties()
							.sword(ToolMaterial.NETHERITE, 14.0F, 8.7F)
							.durability(10500)
							.setId(ResourceKey.create(
									Registries.ITEM,
									Identifier.fromNamespaceAndPath(MOD_ID, "the_razor_of_the_end")
							))
			)
	);
	public static final Item THE_VOIDS_EDGE = Registry.register(
			BuiltInRegistries.ITEM,
			Identifier.fromNamespaceAndPath(MOD_ID, "the_voids_edge"),
			new TheVoidsEdgeItem(
					new Item.Properties()
							.sword(ToolMaterial.NETHERITE, 12.0F, 8.8F)
							.durability(10900)
							.setId(ResourceKey.create(
									Registries.ITEM,
									Identifier.fromNamespaceAndPath(MOD_ID, "the_voids_edge")
							))
			)
	);
	public static final Item CORRUPTED_BLADE = Registry.register(
			BuiltInRegistries.ITEM,
			Identifier.fromNamespaceAndPath(MOD_ID, "corrupted_blade"),
			new CorruptedBladeItem(
					new Item.Properties()
							.sword(ToolMaterial.NETHERITE, 18.0F, 7.8F)
							.durability(43377)
							.setId(ResourceKey.create(
									Registries.ITEM,
									Identifier.fromNamespaceAndPath(MOD_ID, "corrupted_blade")
							))
			)
	);
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
							.title(Component.literal("Utilities"))
							.icon(() -> new ItemStack(LOG_STICK))
							.displayItems((parameters, output) -> {
								output.accept(LOG_STICK);
								output.accept(SULFUR_BLOCK);

							})

							.build()
			);
	public static final Item LOG_GERNADE =
			Registry.register(
					BuiltInRegistries.ITEM,
					Identifier.fromNamespaceAndPath(MOD_ID, "log_gernade"),
					new LogGernadeItem(
							new Item.Properties().setId(
									ResourceKey.create(
											Registries.ITEM,
											Identifier.fromNamespaceAndPath(MOD_ID, "log_gernade")
									)
							)
					));
	public static final CreativeModeTab CHAOS_TAB =
			Registry.register(
					BuiltInRegistries.CREATIVE_MODE_TAB,
					Identifier.fromNamespaceAndPath(MOD_ID, "chaos"),
					CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
							.title(Component.literal("Tools")) // broken items (not literally but to where its really op) \\
							.icon(() -> new ItemStack(TNT_STICK))
							.displayItems((parameters, output) -> {
								output.accept(THE_ENDS_END);
								output.accept(THE_RAZOR_OF_THE_END);
								output.accept(THE_VOIDS_EDGE);
								output.accept(CORRUPTED_BLADE);
								output.accept(RAZOR_OF_HELL);
								output.accept(RAZOR_OF_SMOL);
								output.accept(THE_END_CRYSTALS_BEAM);
							})
							.build()
			);
	public static final CreativeModeTab TOOLS =
			Registry.register(
					BuiltInRegistries.CREATIVE_MODE_TAB,
					Identifier.fromNamespaceAndPath(MOD_ID, "tools"),
					CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
							.title(Component.literal("OP Items")) // broken items (not literally but to where its really op) \\
							.icon(() -> new ItemStack(TNT_STICK))
							.displayItems((parameters, output) -> {
								output.accept(FIREBALL_LAUNCHER);
								output.accept(TNT_STICK);
								output.accept(LOG_GERNADE);
								output.accept(WIND_WAND);
								output.accept(LIGHTNING_BOMB);
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
