package net.dice7000.incomparablyl2.common.trait;

import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import net.dice7000.incomparablyl2.common.registry.IL2Traits;
import net.minecraft.ChatFormatting;

public class IL2Trait extends MobTrait {
    public IL2Trait(ChatFormatting format) {
        super(IL2Traits.formatOrObfuscate(IL2Traits.TheTrialMonolith, format));
    }
}
