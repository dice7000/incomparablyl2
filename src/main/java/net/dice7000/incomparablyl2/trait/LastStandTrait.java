package net.dice7000.incomparablyl2.trait;

import com.mega.uom.util.entity.EntityASMUtil;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import net.dice7000.incomparablyl2.IncomparablyL2;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LastStandTrait extends MobTrait {
    public LastStandTrait() {
        super(ChatFormatting.LIGHT_PURPLE);
    }

    @Override public void initialize(@NotNull LivingEntity mob, int level) {
        super.initialize(mob, level);
        CooldownData.define(mob);
    }

    @Override public void tick(@NotNull LivingEntity mob, int level) {
        super.tick(mob, level);
        if (CooldownData.get(mob) > 0) {
            CooldownData.decrement(mob);
        }
    }

    @Override public void onDeath(int level, @NotNull LivingEntity entity, @NotNull LivingDeathEvent event) {
        super.onDeath(level, entity, event);

        int cooldown = CooldownData.get(entity);
        double rand = entity.getType().is(IncomparablyL2.FORGE_BOSSES) ? 1.0 : Math.random();
        if (entity.isDeadOrDying() && cooldown <= 0 && rand <= 0.2) {
            if (!entity.level().isClientSide) {
                entity.level().playSound(entity, BlockPos.containing(entity.position()),
                        SoundEvents.PLAYER_LEVELUP, SoundSource.HOSTILE, 3.0F, 0.8F);
                entity.setHealth(entity.getMaxHealth());
                EntityASMUtil.setHealthDelta(entity, 0.0F);
                CooldownData.set(entity, 12000);
            }
            entity.deathTime = 0;
        }
    }

    public static class CooldownData {
        private static final Map<LivingEntity, AtomicInteger> hasCooldownEntities = new HashMap<>();

        public static void define(LivingEntity entity) {
            hasCooldownEntities.put(entity, new AtomicInteger(0));
        }
        public static void set(LivingEntity entity, int cooldown) {
            hasCooldownEntities.get(entity).set(cooldown);
        }
        public static int get(LivingEntity entity) {
            return hasCooldownEntities.get(entity).get();
        }
        public static void decrement(LivingEntity entity) {
            set(entity, get(entity) - 1);
        }
    }
}
