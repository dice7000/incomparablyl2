package net.dice7000.incomparablyl2.common.trait;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2hostility.content.logic.TraitEffectCache;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class BerserkerTrait extends IL2Trait {
    public BerserkerTrait(ChatFormatting format) {
        super(format);
    }

    double health; double damage; double armor; double speed;
    @Override public void initialize(LivingEntity mob, int level) {
        super.initialize(mob, level);
        health = mob.getAttributeValue(Attributes.MAX_HEALTH);
        damage = mob.getAttributeValue(Attributes.ATTACK_DAMAGE);
        armor = mob.getAttributeValue(Attributes.ARMOR);
        speed = mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    @Override public void onHurtTarget(int level, LivingEntity attacker, AttackCache cache, TraitEffectCache traitCache) {
        super.onHurtTarget(level, attacker, cache, traitCache);
        updateAttributes(attacker, level);

    }
    @Override public void onHurtByOthers(int level, LivingEntity entity, LivingHurtEvent event) {
        super.onHurtByOthers(level, entity, event);
        updateAttributes(event.getEntity(), level);
    }

    int count = 0;
    private void updateAttributes(LivingEntity mob, int level) {
        count++;
        double applyValue = (2 * level) * count;

        float beforeMaxHealth = mob.getMaxHealth();
        mob.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health + Math.min(applyValue, 30));
        mob.setHealth(mob.getHealth() + (mob.getMaxHealth() - beforeMaxHealth));
        mob.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(damage + applyValue);
        mob.getAttribute(Attributes.ARMOR).setBaseValue(armor + applyValue);
        mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed + Math.min(applyValue / 20, 1));
    }

    @Override public void onDeath(int level, LivingEntity entity, LivingDeathEvent event) {
        super.onDeath(level, entity, event);
        damage = 0; armor = 0; speed = 0; count = 0;
    }
}
