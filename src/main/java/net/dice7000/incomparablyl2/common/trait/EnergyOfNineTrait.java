package net.dice7000.incomparablyl2.common.trait;

import com.test.nosugar.utils.Tail_of_Nine_Handler;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import net.dice7000.incomparablyl2.common.registry.IL2Traits;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;

public class EnergyOfNineTrait extends IL2Trait {
    public EnergyOfNineTrait(ChatFormatting format) {
        super(format);
    }

    @Override public void onHurtTarget(int level, LivingEntity at, AttackCache cache, TraitEffectCache traitCache) {
        super.onHurtTarget(level, at, cache, traitCache);
        if (at.level().isClientSide) return;
        LivingEntity attacker = cache.getAttacker();
        LivingEntity target = cache.getAttackTarget();
        if (target != null && attacker != null) {
            if (Math.random() < 0.1) {
                target.level().playSound(null, target.blockPosition(),
                        SoundEvents.GENERIC_EAT, SoundSource.HOSTILE, 8.0F, 1.0F);
                Tail_of_Nine_Handler.applyHit(target, attacker);
            }
        }
    }
}
