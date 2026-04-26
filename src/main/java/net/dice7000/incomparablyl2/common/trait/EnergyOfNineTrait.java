package net.dice7000.incomparablyl2.common.trait;

import com.test.nosugar.utils.Tail_of_Nine_Handler;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EnergyOfNineTrait extends IL2Trait {
    public EnergyOfNineTrait() {
        super(ChatFormatting.RED);
    }

    @Override public void onHurtTarget(int level, LivingEntity at, AttackCache cache, TraitEffectCache traitCache) {
        super.onHurtTarget(level, at, cache, traitCache);
        if (at.level().isClientSide) return;
        LivingEntity attacker = cache.getAttacker();
        LivingEntity target = cache.getAttackTarget();
        if (target != null && attacker != null) {
            if (Math.random() < 0.1 || attacker.isDeadOrDying()) {
                target.level().playSound(null, target.blockPosition(),
                        SoundEvents.GENERIC_EAT, SoundSource.HOSTILE, 8.0F, 1.0F);
                Tail_of_Nine_Handler.applyHit(target, attacker);
                if (!attacker.isDeadOrDying()) {
                    for (int i = 0; i <= (Math.round(Math.random() * 7.2)); i++) Tail_of_Nine_Handler.applyHit(target, attacker);
                }
            }
        }
    }
    @Override public void onDeath(int level, LivingEntity entity, LivingDeathEvent event) {
        super.onDeath(level, entity, event);

        if (Math.random() >= 0.3) return;
        entity.level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
    }
}
