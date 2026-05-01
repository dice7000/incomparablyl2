package net.dice7000.incomparablyl2.trait;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BerserkerTrait extends MobTrait {
    public BerserkerTrait() {
        super(ChatFormatting.RED);
    }

    double health; double damage; double armor; double speed;
    @Override public void initialize(@NotNull LivingEntity mob, int level) {
        super.initialize(mob, level);
        health = mob.getAttributeValue(Attributes.MAX_HEALTH);
        damage = mob.getAttributeValue(Attributes.ATTACK_DAMAGE);
        armor = mob.getAttributeValue(Attributes.ARMOR);
        speed = mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    @Override public void onHurtTarget(int level, @NotNull LivingEntity attacker, @NotNull AttackCache cache, @NotNull TraitEffectCache traitCache) {
        super.onHurtTarget(level, attacker, cache, traitCache);
        updateAttributes(attacker, level);
    }
    @Override public void onHurtByOthers(int level, @NotNull LivingEntity entity, @NotNull LivingHurtEvent event) {
        super.onHurtByOthers(level, entity, event);
        updateAttributes(event.getEntity(), level);
    }

    int count = 0;
    private void updateAttributes(LivingEntity mob, int level) {
        count++;
        double applyValue = (2 * level) * count;

        float beforeMaxHealth = mob.getMaxHealth();
        Objects.requireNonNull(mob.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(health + Math.min(applyValue, 30));
        mob.setHealth(mob.getHealth() + (mob.getMaxHealth() - beforeMaxHealth));
        Objects.requireNonNull(mob.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(damage + applyValue);
        Objects.requireNonNull(mob.getAttribute(Attributes.ARMOR)).setBaseValue(armor + applyValue);
        Objects.requireNonNull(mob.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(speed + Math.min(applyValue / 20, 1));
    }

    @Override public void onDeath(int level, @NotNull LivingEntity entity, @NotNull LivingDeathEvent event) {
        super.onDeath(level, entity, event);
        damage = 0; armor = 0; speed = 0; count = 0;
    }
}
