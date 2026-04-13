package net.dice7000.incomparablyl2.common.trait;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PiercesShieldTrait extends IL2Trait {
    public PiercesShieldTrait(ChatFormatting format) {
        super(format);
    }

    @Override
    public void onHurtTarget(int level, LivingEntity attacker, AttackCache cache, TraitEffectCache traitCache) {
        super.onHurtTarget(level, attacker, cache, traitCache);
        if (attacker.level().isClientSide) return;
        LivingHurtEvent event = cache.getLivingHurtEvent();
        if (event != null) {
            float amount = event.getAmount();
            if (amount > 0.0F) {
                float specialAmount = (float) (amount * (0.2 * level));
                LivingEntity target = cache.getAttackTarget();
                event.setAmount(amount - specialAmount);
                target.setHealth(target.getHealth() - specialAmount);
            }
        }
    }
}
