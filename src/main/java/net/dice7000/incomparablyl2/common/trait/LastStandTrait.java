package net.dice7000.incomparablyl2.common.trait;

import com.mega.uom.util.entity.EntityASMUtil;
import net.dice7000.incomparablyl2.common.registry.IL2Traits;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LastStandTrait extends IL2Trait {
    public LastStandTrait(ChatFormatting format) {
        super(format);
    }

    int cooldown = 0;
    boolean shouldRunLastStand = true;
    @Override public void onDeath(int level, LivingEntity entity, LivingDeathEvent event) {
        super.onDeath(level, entity, event);
        if (!shouldRunLastStand) {
            cooldown++;
            if (cooldown >= 12000) {
                cooldown = 0;
                shouldRunLastStand = true;
            }
        }

        if (entity.isDeadOrDying() && shouldRunLastStand && entity.getType().is(IL2Traits.FORGE_BOSSES)) {
            if (!entity.level().isClientSide) {
                entity.level().playSound(entity, BlockPos.containing(entity.position()),
                        SoundEvents.PLAYER_LEVELUP, SoundSource.HOSTILE, 3.0F, 0.8F);
                entity.setHealth(entity.getMaxHealth());
                EntityASMUtil.setHealthDelta(entity, 0.0F);
            }
            entity.deathTime = 0;
            shouldRunLastStand = false;
        }
    }
}
