package net.dice7000.incomparablyl2.common.trait;

import com.sakurafuld.hyperdaimc.content.HyperSounds;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import net.dice7000.incomparablyl2.common.registry.IL2Traits;
import net.dice7000.incomparablyl2.util.IL2Util;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;

public class RookieWriterTrait extends IL2Trait {
    public RookieWriterTrait(ChatFormatting format) {
        super(format);
    }

    @Override public void onHurtTarget(int level, LivingEntity at, AttackCache cache, TraitEffectCache traitCache) {
        super.onHurtTarget(level, at, cache, traitCache);
        if (at.level().isClientSide) return;
        LivingEntity attacker = cache.getAttacker();
        LivingEntity target = cache.getAttackTarget();
        if (target != null && attacker != null) {
            if (Math.random() < (0.001 * Math.pow(10, (level - 1)))) {
                int ver = ModList.get().getModContainerById(IL2Util.Hyperlink).orElseThrow().getModInfo().getVersion().getMinorVersion();
                attacker.level().playSound(null, BlockPos.containing(target.position()),
                        HyperSounds.NOVEL.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
                if (ver >= 3) {
                    com.sakurafuld.hyperdaimc.content.hyper.novel.system.
                            NovelHandler.novelize(attacker, target, true);
                } else {
                    //com.sakurafuld.hyperdaimc.content.hyper.novel.
                            //NovelHandler.novelize(attacker, target, true);
                }
            }
        }
    }
}
