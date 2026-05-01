package net.dice7000.incomparablyl2.trait;

import com.mega.uom.common.damagesource.ModDamageSources;
import com.mega.uom.util.entity.EntityActuallyHurt;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FEDamageTrait extends MobTrait {
    public FEDamageTrait() {
        super(ChatFormatting.LIGHT_PURPLE);
    }

    @Override public void onHurtTarget(int level, @NotNull LivingEntity attacker, @NotNull AttackCache cache, @NotNull TraitEffectCache traitCache) {
        super.onHurtTarget(level, attacker, cache, traitCache);
        LivingEntity target = Objects.requireNonNull(cache.getLivingHurtEvent()).getEntity();
        if (target == null) return;
        EntityActuallyHurt hurt = new EntityActuallyHurt(target);
        hurt.actuallyHurt(ModDamageSources.causeDeathFeDamage(attacker),
                ((float)((2 * Math.max(level, 1)) + attacker.getRandom().nextInt(5)) + target.getMaxHealth() * 0.005F + target.getHealth() * 0.005F) * 0.6F, true);
    }
}
