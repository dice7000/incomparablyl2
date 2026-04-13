package net.dice7000.incomparablyl2.common.trait;

import com.sakurafuld.hyperdaimc.content.hyper.novel.system.NovelHandler;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.data.LHConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class TestTrait extends IL2Trait {
    public TestTrait(ChatFormatting format) {
        super(format);
    }

    @Override public void onHurtTarget(int level, LivingEntity attacker, AttackCache cache, TraitEffectCache traitCache) {
        super.onHurtTarget(level, attacker, cache, traitCache);
        LivingEntity target = cache.getAttackTarget();

        target.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.END_PORTAL_SPAWN, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    @Override public void onDamaged(int level, LivingEntity mob, AttackCache cache) {
        super.onDamaged(level, mob, cache);
        LivingEntity target = cache.getAttackTarget();
        LivingEntity attacker = cache.getAttacker();
        if (target != null && attacker != null) NovelHandler.novelize(target, attacker, true);
    }

    public void addDetail(List<Component> list) {
        list.add(Component.translatable(this.getDescriptionId() + ".desc", this.mapLevel((i) -> {
            double var10001 = (double) i;
            return Component.literal("" + (int)Math.round((double)100.0F * var10001 * LHConfig.COMMON.reflectFactor.get())).withStyle(ChatFormatting.AQUA);
        })).withStyle(ChatFormatting.GRAY));
    }
}
