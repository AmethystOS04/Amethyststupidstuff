package amethyst.logger.items;

import amethyst.logger.RandomLogger;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LogGernadeProjectile extends Snowball {

    public LogGernadeProjectile(
            Level level,
            LivingEntity owner,
            ItemStack itemStack
    ) {
        super(level, owner, itemStack);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (!this.level().isClientSide()) {

            for (int i = 0; i < 5; i++) {
                String message = RandomLogger.getRandomMessage();

                for (var player : this.level().players()) {
                    player.sendSystemMessage(
                            Component.literal(message)
                    );
                }
            }

            this.discard();
        }
    }
}