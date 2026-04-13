package net.dice7000.incomparablyl2.mixin;

import net.minecraftforge.fml.ModList;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class IL2MixinPlugin implements IMixinConfigPlugin {
    @Override public void onLoad(String mixinPackage) {
    }
    @Override public String getRefMapperConfig() {
        return "";
    }
    @Override public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals("net.dice7000.incomparablyl2.mixin.mixin.HLSpecialCodeForFEMixin")) {
            if (ModList.get().isLoaded("hyperdaimc")) {
                ArtifactVersion version = ModList.get().getModContainerById("hyperdaimc").orElseThrow().getModInfo().getVersion();
                return version.getMinorVersion() == 3;
            }
        }
        return true;
    }

    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }
    @Override public List<String> getMixins() {
        return List.of("HLSpecialCodeForFEMixin");
    }
    @Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
    @Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
