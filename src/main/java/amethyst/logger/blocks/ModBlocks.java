package amethyst.logger.blocks;

import amethyst.logger.RandomLogger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
public class ModBlocks {

    public static final Block SULFUR = registerBlock("sulfur", Blocks.STONE);
    public static final Block POLISHED_SULFUR = registerBlock("polished_sulfur", Blocks.STONE);
    public static final Block CHISELED_SULFUR = registerBlock("chiseled_sulfur", Blocks.STONE);

    public static final Block SULFUR_BRICKS = registerBlock("sulfur_bricks", Blocks.STONE_BRICKS);

    public static final Block SULFUR_SLAB = registerSlab("sulfur_slab", Blocks.STONE);
    public static final Block SULFUR_STAIRS = registerStairs("sulfur_stairs", SULFUR, Blocks.STONE);
    public static final Block SULFUR_WALL = registerWall("sulfur_wall", Blocks.STONE);

    public static final Block SULFUR_BRICK_SLAB = registerSlab("sulfur_brick_slab", Blocks.STONE_BRICKS);
    public static final Block SULFUR_BRICK_STAIRS = registerStairs("sulfur_brick_stairs", SULFUR_BRICKS, Blocks.STONE_BRICKS);
    public static final Block SULFUR_BRICK_WALL = registerWall("sulfur_brick_wall", Blocks.STONE_BRICKS);

    public static final Block POTENT_SULFUR = registerBlock("potent_sulfur", Blocks.STONE);

    public static final Block CINNABAR = registerBlock("cinnabar", Blocks.STONE);
    public static final Block CHISELED_CINNABAR = registerBlock("chiseled_cinnabar", Blocks.STONE);

    public static final Block CINNABAR_BRICKS = registerBlock("cinnabar_bricks", Blocks.STONE_BRICKS);

    public static final Block CINNABAR_SLAB = registerSlab("cinnabar_slab", Blocks.STONE);
    public static final Block CINNABAR_STAIRS = registerStairs("cinnabar_stairs", CINNABAR, Blocks.STONE);
    public static final Block CINNABAR_WALL = registerWall("cinnabar_wall", Blocks.STONE);

    public static final Block CINNABAR_BRICK_SLAB = registerSlab("cinnabar_brick_slab", Blocks.STONE_BRICKS);
    public static final Block CINNABAR_BRICK_STAIRS = registerStairs("cinnabar_brick_stairs", CINNABAR_BRICKS, Blocks.STONE_BRICKS);
    public static final Block CINNABAR_BRICK_WALL = registerWall("cinnabar_brick_wall", Blocks.STONE_BRICKS);
    private static Block registerBlock(String name, Block base) {
        Identifier id = Identifier.fromNamespaceAndPath(RandomLogger.MOD_ID, name);

        Block block = new Block(
                Block.Properties.ofFullCopy(base)
                        .setId(ResourceKey.create(Registries.BLOCK, id))
        );

        return register(id, block);
    }

    private static Block registerSlab(String name, Block base) {
        Identifier id = Identifier.fromNamespaceAndPath(RandomLogger.MOD_ID, name);

        Block block = new SlabBlock(
                Block.Properties.ofFullCopy(base)
                        .setId(ResourceKey.create(Registries.BLOCK, id))
        );

        return register(id, block);
    }

    private static Block registerWall(String name, Block base) {
        Identifier id = Identifier.fromNamespaceAndPath(RandomLogger.MOD_ID, name);

        Block block = new WallBlock(
                Block.Properties.ofFullCopy(base)
                        .setId(ResourceKey.create(Registries.BLOCK, id))
        );

        return register(id, block);
    }

    private static Block registerStairs(String name, Block parent, Block base) {
        Identifier id = Identifier.fromNamespaceAndPath(RandomLogger.MOD_ID, name);

        Block block = new StairBlock(
                parent.defaultBlockState(),
                Block.Properties.ofFullCopy(base)
                        .setId(ResourceKey.create(Registries.BLOCK, id))
        );

        return register(id, block);
    }

    private static Block register(Identifier id, Block block) {
        Registry.register(
                BuiltInRegistries.ITEM,
                id,
                new BlockItem(
                        block,
                        new Item.Properties().setId(
                                ResourceKey.create(Registries.ITEM, id)
                        )
                )
        );

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void initialize() {
        RandomLogger.LOGGER.info("Registering blocks");
    }

}
