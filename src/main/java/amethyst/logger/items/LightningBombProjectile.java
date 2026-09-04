package amethyst.logger.items;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LightningBombProjectile extends Snowball {

    public LightningBombProjectile(
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

            LightningBolt lightning =
                    new LightningBolt(
                            EntityTypes.LIGHTNING_BOLT,
                            this.level()
                    );

            lightning.setPos(
                    hitResult.getLocation().x(),
                    hitResult.getLocation().y(),
                    hitResult.getLocation().z()
            );

            this.level().addFreshEntity(lightning);

            this.discard();
        }
    }
}