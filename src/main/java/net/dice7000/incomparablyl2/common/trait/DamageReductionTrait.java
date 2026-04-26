package net.dice7000.incomparablyl2.common.trait;

import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class DamageReductionTrait extends IL2Trait {
    public DamageReductionTrait() {
        super(ChatFormatting.YELLOW);
    }

    @Override public void onHurtByOthers(int level, LivingEntity entity, LivingHurtEvent event) {
        super.onHurtByOthers(level, entity, event);
        LivingEntity target = event.getEntity();
        if (target != null && level > 0) {
            float amount = event.getAmount();
            if (amount > 0.0F) {
                if (amount > (20.0F - (level * 5.0F))) amount = (20.0F - (level * 5.0F));
                event.setAmount(amount);
            }
        }
    }
}
