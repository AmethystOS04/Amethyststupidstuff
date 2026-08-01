package amethyst.logger.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireBallLauncherItem extends Item {

    public FireBallLauncherItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public boolean useOnRelease(ItemStack stack) {
        return true;
    }

    private long lastFireTick = -1;

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseTicks) {
        if (level.isClientSide()) return;
        if (!(entity instanceof Player player)) return;

        if (level.getGameTime() == lastFireTick) return;
        lastFireTick = level.getGameTime();

        spawnFireball(level, player);
    }

    private void spawnFireball(Level level, Player player) {
        System.out.println("spawn fireball " + level.getGameTime());
        Vec3 direction = player.getLookAngle();

        LargeFireball fireball = new LargeFireball(
                level,
                player,
                direction,
                1
        );

        fireball.setPos(
                player.getX() + direction.x,
                player.getEyeY() - 0.2 + direction.y,
                player.getZ() + direction.z
        );

        level.addFreshEntity(fireball);
    }
    

}