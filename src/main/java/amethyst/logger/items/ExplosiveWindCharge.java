package amethyst.logger.items;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExplosiveWindCharge extends WindCharge {

    public ExplosiveWindCharge(
            Player player,
            Level level,
            double x,
            double y,
            double z
    ) {
        super(player, level, x, y, z);
    }

    @Override
    protected void explode(Vec3 position) {
        this.level().explode(
                this,
                null,
                null,
                position.x(),
                position.y(),
                position.z(),
                5.0F,
                false,
                Level.ExplosionInteraction.TRIGGER,
                ParticleTypes.GUST_EMITTER_SMALL,
                ParticleTypes.GUST_EMITTER_LARGE,
                net.minecraft.util.random.WeightedList.of(),
                SoundEvents.WIND_CHARGE_BURST
        );
    }
}