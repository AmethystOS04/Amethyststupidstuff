package amethyst.logger.items;

import amethyst.logger.RandomLogger;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.Random;

public class LogStickItem extends Item {

    private static final Random RANDOM = new Random();

    public LogStickItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {
            player.sendSystemMessage(
                    Component.literal(RandomLogger.getRandomMessage())
            );
        }

        return InteractionResult.SUCCESS;
    }
}