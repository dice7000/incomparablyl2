package net.dice7000.incomparablyl2.trait;

import com.sakurafuld.hyperdaimc.content.HyperSounds;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import net.dice7000.incomparablyl2.IncomparablyL2;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public class RookieWriterTrait extends MobTrait {
    public RookieWriterTrait() {
        super(ChatFormatting.DARK_RED);
    }

    @Override public void onHurtTarget(int level, @NotNull LivingEntity attacker, @NotNull AttackCache cache, @NotNull TraitEffectCache traitCache) {
        super.onHurtTarget(level, attacker, cache, traitCache);
        if (attacker.level().isClientSide) return;
        LivingEntity target = cache.getAttackTarget();
        if ((Math.random() < (0.001 * Math.pow(10, (level - 1)))) || (attacker.isDeadOrDying() && Math.random() < 0.5)) {
            int ver = ModList.get().getModContainerById(IncomparablyL2.Hyperlink).orElseThrow().getModInfo().getVersion().getMinorVersion();
            attacker.level().playSound(null, BlockPos.containing(target.position()),
                    HyperSounds.NOVEL.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
            doNovelize(ver, attacker, target);
        }
    }

    private void doNovelize(int ver, LivingEntity attacker, LivingEntity target) {
        if (ver >= 3) {
            com.sakurafuld.hyperdaimc.content.hyper.novel.system.
                    NovelHandler.novelize(attacker, target, true);
        } else {
            com.sakurafuld.hyperdaimc.content.hyper.novel.
                    NovelHandler.novelize(attacker, target, true);
        }
    }

    @Override public void onDeath(int level, @NotNull LivingEntity entity, @NotNull LivingDeathEvent event) {
        super.onDeath(level, entity, event);
        if (Math.random() < 0.3) entity.level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
    }
}
