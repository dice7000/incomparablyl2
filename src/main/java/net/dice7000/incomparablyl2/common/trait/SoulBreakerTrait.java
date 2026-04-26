package net.dice7000.incomparablyl2.common.trait;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import io.github.kosianodangoo.trialmonolith.TrialMonolithConfig;
import io.github.kosianodangoo.trialmonolith.common.helper.EntityHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class SoulBreakerTrait extends IL2Trait {
    public SoulBreakerTrait() {
        super(ChatFormatting.DARK_BLUE);
    }

    @Override public void onHurtTarget(int level, LivingEntity at, AttackCache cache, TraitEffectCache traitCache) {
        super.onHurtTarget(level, at, cache, traitCache);
        if (at.level().isClientSide) return;
        LivingEntity attacker = cache.getAttacker();
        LivingEntity target = cache.getAttackTarget();
        if (target != null && attacker != null) {
            attacker.level().playSound(null, BlockPos.containing(target.position()),
                    SoundEvents.FIREWORK_ROCKET_BLAST, SoundSource.HOSTILE, 8.0F, 1.0F);
            EntityHelper.addSoulDamage(target, attacker.isDeadOrDying() ? 0.5F : TrialMonolithConfig.smallBeamSoulDamage);
        }
    }

    @Override public void onDeath(int level, LivingEntity entity, LivingDeathEvent event) {
        super.onDeath(level, entity, event);

        if (Math.random() >= 0.3) return;
        entity.level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
    }
}
