package amethyst.logger.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import org.jspecify.annotations.Nullable;
import java.util.List;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

public class TntStickItem extends Item {

    public TntStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseTicks) {

        if (level.isClientSide()) return;

        if (!(entity instanceof Player player)) return;

        // always fire 2 TNT
        spawnTnt(level, player, 0);
        spawnTnt(level, player, 0);

        Holder<Enchantment> multishot =
                level.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .getOrThrow(Enchantments.MULTISHOT);

        int multishotLevel =
                EnchantmentHelper.getItemEnchantmentLevel(multishot, stack);

        for (int i = 1; i <= multishotLevel; i++) {
            spawnTnt(level, player, -10F * i);
            spawnTnt(level, player, 10F * i);
        }
    }


    private void spawnTnt(Level level, Player player, float yawOffset) {
        PrimedTnt tnt = new PrimedTnt(
                level,
                player.getX(),
                player.getEyeY(),
                player.getZ(),
                player
        );

        Vec3 direction = player.getLookAngle().yRot((float) Math.toRadians(yawOffset));

        tnt.setDeltaMovement(direction.scale(2.5));

        level.addFreshEntity(tnt);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public boolean useOnRelease(ItemStack stack) {
        return true;
    }
}