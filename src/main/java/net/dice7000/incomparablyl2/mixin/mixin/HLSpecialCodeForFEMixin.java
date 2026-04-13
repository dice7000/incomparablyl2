package net.dice7000.incomparablyl2.mixin.mixin;

import com.sakurafuld.hyperdaimc.addon.fantasy_ending.HyperFantasyEnding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HyperFantasyEnding.class, remap = false)
public class HLSpecialCodeForFEMixin {
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void init(CallbackInfo ci) {
        Logger logger = LoggerFactory.getLogger("dice7000");
        logger.info(
                        """
                        
                        夢幻終焉ダメージの汎用対策ができなくて、専用対策を使った人がいるんですよ～
                        なぁ～にぃ～！？やっちまったなぁ～！
                        男は黙って、汎用対策！
                        男は黙って、汎用対策！
                        
                        汎用対策ができなかったからって、専用対策を入れるのはやめてください！
                        こっちで専用対策消しときますね～
                        """
        );
    }

    @Inject(method = "actuallyHurt", at = @At("HEAD"), cancellable = true)
    public void actuallyHurtInject(CallbackInfo ci) {
        ci.cancel();
    }
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tickInject(CallbackInfo ci) {
        ci.cancel();
    }
}
