package net.dice7000.incomparablyl2.trait;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import io.github.kosianodangoo.trialmonolith.TrialMonolithConfig;
import io.github.kosianodangoo.trialmonolith.common.helper.EntityHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

public class SoulBreakerTrait extends MobTrait {
    public SoulBreakerTrait() {
        super(ChatFormatting.DARK_BLUE);
    }

    @Override public void onHurtTarget(int level, @NotNull LivingEntity attacker, @NotNull AttackCache cache, @NotNull TraitEffectCache traitCache) {
        super.onHurtTarget(level, attacker, cache, traitCache);
        if (attacker.level().isClientSide) return;
        LivingEntity target = cache.getAttackTarget();
        attacker.level().playSound(null, BlockPos.containing(target.position()),
                SoundEvents.FIREWORK_ROCKET_BLAST, SoundSource.HOSTILE, 8.0F, 1.0F);
        EntityHelper.addSoulDamage(target, attacker.isDeadOrDying() ? 0.5F : TrialMonolithConfig.smallBeamSoulDamage);

    }

    @Override public void onDeath(int level, @NotNull LivingEntity entity, @NotNull LivingDeathEvent event) {
        super.onDeath(level, entity, event);
        if (Math.random() < 0.3) entity.level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
    }
}
