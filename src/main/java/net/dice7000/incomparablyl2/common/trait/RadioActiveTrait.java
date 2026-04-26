package net.dice7000.incomparablyl2.common.trait;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.ChatFormatting;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.Objects;

public class RadioActiveTrait extends IL2Trait {
    public RadioActiveTrait() {
        super(ChatFormatting.GREEN);
    }

    @Override public void onHurtTarget(int level, LivingEntity attacker, AttackCache cache, TraitEffectCache traitCache) {
        super.onHurtTarget(level, attacker, cache, traitCache);

        LivingEntity target = Objects.requireNonNull(cache.getLivingAttackEvent()).getEntity();
        if (target.equals(attacker)) return;

        double radiation; double valueRand; double limitRand;
        if (attacker.isDeadOrDying()) {
            valueRand = (Math.random() * 0.4) + 0.6; limitRand = (Math.random() * 0.2) + 0.8;
        } else {
            if (Math.random() <= 0.2) return;
            valueRand = Math.random(); limitRand = Math.max(Math.random(), Double.MIN_VALUE);
        }
        radiation = Mth.clamp(((valueRand * Double.MAX_VALUE) - (Double.MAX_VALUE / 2)) * 2, (1.0E-7 * (limitRand * 1.0E10)), Double.MAX_VALUE / limitRand);
        target.getCapability(Capabilities.RADIATION_ENTITY, null).ifPresent(iRadiationEntity -> iRadiationEntity.set(radiation));
    }

    @Override public void onDeath(int level, LivingEntity entity, LivingDeathEvent event) {
        super.onDeath(level, entity, event);

        if (Math.random() >= 0.3) return;
        entity.level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
    }
}
