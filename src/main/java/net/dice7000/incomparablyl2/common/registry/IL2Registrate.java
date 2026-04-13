package net.dice7000.incomparablyl2.common.registry;

import com.google.common.base.Suppliers;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.tterrag.registrate.util.nullness.NonnullType;
import dev.xkmc.l2hostility.content.config.TraitConfig;
import dev.xkmc.l2hostility.content.item.traits.TraitSymbol;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.L2Hostility;
import dev.xkmc.l2hostility.init.data.LHTagGen;
import dev.xkmc.l2library.base.NamedEntry;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.l2serial.serialization.custom_handler.RLClassHandler;
import dev.xkmc.l2serial.util.Wrappers;
import net.dice7000.incomparablyl2.IncomparablyL2;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
public class IL2Registrate extends AbstractRegistrate<IL2Registrate> {
    private final List<String> LIST = new ArrayList<>();
    public final List<Consumer<ConfigDataProvider.Collector>> CONFIGS = new ArrayList<>();

    public IL2Registrate() {
        super(IncomparablyL2.MOD_ID);
    }
    public static IL2Registrate INSTANCE = new IL2Registrate();
    public final <T extends MobTrait> IL2TraitBuilder regTrait(String id, NonNullSupplier<T> sup, NonNullFunction<ResourceLocation, TraitConfig> config) {
        this.LIST.add(id);
        return entry(id, (cb) -> new IL2TraitBuilder<>(
                this, this, id, cb, sup, config))
                .item(new TraitSymbol(new Item.Properties()))
                .build();
    }

    public <E extends NamedEntry<E>> IL2Registrate.RegistryInstance<E> newIL2Registry(String id, Class<?> cls, Consumer<RegistryBuilder<E>> cons) {
        ResourceKey<Registry<E>> key = this.makeRegistry(id, () -> {
            RegistryBuilder<E> ans = new RegistryBuilder<>();
            ans.onCreate((r, s) -> new RLClassHandler(cls, () -> r));
            cons.accept(ans);
            return ans;
        });
        return new IL2Registrate.RegistryInstance<>(Suppliers.memoize(() -> RegistryManager.ACTIVE.getRegistry(key)), key);
    }

    public <E extends NamedEntry<E>> IL2Registrate.RegistryInstance<E> newIL2Registry(String id, Class<?> cls) {
        return this.newIL2Registry(id, cls, (e) -> {
        });
    }

    public void addTraitConfig(Consumer<ConfigDataProvider.Collector> cons) {
        this.CONFIGS.add(cons);
    }

    public static class IL2TraitBuilder<T extends MobTrait, P> extends AbstractBuilder<MobTrait, T, P, IL2TraitBuilder<T, P>> {
        private final NonNullSupplier<T> sup;

        public IL2TraitBuilder(IL2Registrate owner, P parent, String name, BuilderCallback callback, NonNullSupplier<T> sup, Function<ResourceLocation, TraitConfig> config) {
            super(owner, parent, name, callback, IL2Traits.TRAITS.key());
            this.sup = sup;
            ResourceLocation rl = IncomparablyL2.IL2Location(this.getName());
            TraitConfig entry = config.apply(rl);
            owner.addTraitConfig((e) -> e.add(L2Hostility.TRAIT, rl, entry));
        }

        protected RegistryEntry<T> createEntryWrapper(RegistryObject<T> delegate) {
            return new RegistryEntry<>(Wrappers.cast(this.getOwner()), delegate);
        }

        public IL2TraitBuilder<T, P> lang(String name) {
            return lang(NamedEntry::getDescriptionId, name);
        }

        public <I extends TraitSymbol> ItemBuilder<I, IL2TraitBuilder<T, P>> item(
                TraitSymbol sup) {
            return (this.getOwner().item(this, this.getName(), sup)
                    .model((ctx, pvd) -> pvd.generated(ctx,
                            new ResourceLocation[]{pvd.modLoc("item/bg"),
                                    pvd.modLoc("item/trait/" + ctx.getName())}))
                    .setData(ProviderType.LANG, NonNullBiConsumer.noop()))
                    .tag(LHTagGen.TRAIT_ITEM);
        }

        protected @NonnullType @NotNull T createEntry() {
            return (T) (this.sup.get());
        }

        public IL2TraitBuilder<T, P> desc(String s) {
            this.getOwner().addRawLang("trait." + this.getOwner().getModid() + "." + this.getName() + ".desc", s);
            return this;
        }
    }

    public record RegistryInstance<E extends NamedEntry<E>>(Supplier<IForgeRegistry<E>> supplier, ResourceKey<Registry<E>> key) implements Supplier<IForgeRegistry<E>> {
        public IForgeRegistry<E> get() {
            return this.supplier().get();
        }
    }
}

 */
