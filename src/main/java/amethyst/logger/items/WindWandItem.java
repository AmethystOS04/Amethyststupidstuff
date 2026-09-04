package amethyst.logger.items;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.enchantment.Enchantment;
import java.util.Random;

public class WindWandItem extends Item {

    private static final Random RANDOM = new Random();

    public WindWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {

            Holder<Enchantment> multishot =
                    level.registryAccess()
                            .lookupOrThrow(Registries.ENCHANTMENT)
                            .getOrThrow(Enchantments.MULTISHOT);

            int multishotLevel =
                    EnchantmentHelper.getItemEnchantmentLevel(multishot, stack);

            spawnWindCharge(level, player, 0);

            for (int i = 1; i <= multishotLevel; i++) {
                spawnWindCharge(level, player, -10F * i);
                spawnWindCharge(level, player, 10F * i);
            }

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }

    private void spawnWindCharge(Level level, Player player, float yawOffset) {

        Vec3 direction = player.getLookAngle()
                .yRot((float) Math.toRadians(yawOffset));

        WindCharge charge;

        if (RANDOM.nextFloat() < 0.15F) {
            charge = new ExplosiveWindCharge(
                    player,
                    level,
                    player.getX(),
                    player.getEyeY(),
                    player.getZ()
            );
        } else {
            charge = new WindCharge(
                    player,
                    level,
                    player.getX(),
                    player.getEyeY(),
                    player.getZ()
            );
        }

        charge.setDeltaMovement(direction.scale(1.5));
        level.addFreshEntity(charge);
    }
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}