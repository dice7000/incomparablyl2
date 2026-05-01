package net.dice7000.incomparablyl2.trait;

import com.mega.uom.common.capability.ModCapabilities;
import com.mega.uom.common.capability.entity.runic.IRunicShieldCapability;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class RunicShieldTrait extends MobTrait {
    public RunicShieldTrait() {
        super(ChatFormatting.LIGHT_PURPLE);
    }

    @Override public void tick(@NotNull LivingEntity mob, int level) {
        super.tick(mob, level);
        IRunicShieldCapability state = ModCapabilities.getCapability(mob, ModCapabilities.MAGIC_SHIELD_EC);
        if (state != null && mob.tickCount % 25 == 0) {
            state.setMaxLifeTime(20481L);
            state.setPerCooldowns(20);
            state.setDamageResistance(8.0F * Math.max(level, 1));
            state.setOutResistance(0.25F);
            state.setSpawnRunic(false);
            state.setLevel((short)4936);
        }
    }
}
