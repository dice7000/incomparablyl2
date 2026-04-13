package net.dice7000.incomparablyl2.common.registry;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.l2hostility.content.config.TraitConfig;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.L2Hostility;
import dev.xkmc.l2hostility.init.data.LHTagGen;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import dev.xkmc.l2library.base.L2Registrate;
import net.dice7000.incomparablyl2.common.trait.*;
import net.dice7000.incomparablyl2.util.IL2Util;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.ModList;

public class IL2Traits {
    public static final L2Registrate.RegistryInstance<MobTrait> TRAITS;

    public static final RegistryEntry<PiercesShieldTrait> PIERCES_SHIELD;
    public static final RegistryEntry<DamageReductionTrait> DAMAGE_REDUCTION;
    public static final RegistryEntry<BerserkerTrait> BERSERKER;

    public static final RegistryEntry<FEDamageTrait> FE_DAMAGE;
    public static final RegistryEntry<RunicShieldTrait> RUNIC_SHIELD;
    public static final RegistryEntry<LastStandTrait> LAST_STAND;
    public static final RegistryEntry<SoulBreakerTrait> SOUL_BREAKER;
    public static final RegistryEntry<RookieWriterTrait> ROOKIE_WRITER;
    public static final RegistryEntry<EnergyOfNineTrait> ENERGY_OF_NINE;

    public static boolean notLoad(String modid) {
        return !ModList.get().isLoaded(modid);
    }
    public static final TagKey<EntityType<?>> FORGE_BOSSES = TagKey.create(Registries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath("forge", "bosses"));

    static {
        TRAITS = LHTraits.TRAITS;

        PIERCES_SHIELD = L2Hostility.REGISTRATE.regTrait
                        ("piercesshield", () -> new PiercesShieldTrait(ChatFormatting.GREEN), (id) -> new TraitConfig(
                                id, 80, 100, 5, 20)).desc("When dealing damage, ignore 20% of the target's shield per level")
                .lang("PiercesShield").register();
        DAMAGE_REDUCTION = L2Hostility.REGISTRATE.regTrait
                        ("damagereduction", () -> new DamageReductionTrait(ChatFormatting.YELLOW), (id) -> new TraitConfig(
                                id, 80, 100, 4, 20)).desc("When damaged, reduce incoming damage by 20% per level")
                .lang("DamageReduction").register();
        BERSERKER = L2Hostility.REGISTRATE.regTrait
                        ("berserker", () -> new BerserkerTrait(ChatFormatting.RED), (id) -> new TraitConfig(
                                id, 100, 100, 3, 30)).desc("Attack damage, armor, and action speed become three times higher")
                .lang("Berserker").register();

        FE_DAMAGE = notLoad(IL2Util.FantasyEnding) ? null : L2Hostility.REGISTRATE.regTrait
                        ("fedamage", () -> new FEDamageTrait(ChatFormatting.LIGHT_PURPLE), (id) -> new TraitConfig(
                                id, 20, 100, 5, 20)).desc("When dealing damage, deal an additional FantasyEnding damage")
                .lang("FEDamage").register();
        RUNIC_SHIELD = notLoad(IL2Util.FantasyEnding) ? null : L2Hostility.REGISTRATE.regTrait
                ("runicshield", () -> new RunicShieldTrait(ChatFormatting.LIGHT_PURPLE), (id) -> new TraitConfig(
                        id, 50, 100, 5, 50)).desc("When damaged, gain a runic shield that absorbs damage and prevents knockback")
                .lang("RunicShield").register();
        LAST_STAND = notLoad(IL2Util.FantasyEnding) ? null : L2Hostility.REGISTRATE.regTrait
                ("laststand", () -> new LastStandTrait(ChatFormatting.LIGHT_PURPLE), (id) -> new TraitConfig(
                        id, 1, 100000, 1, 1).addBlacklist((e) -> e.addTag(FORGE_BOSSES)))
                            .desc("When dying, restore to 4% health and become invulnerable for a short time. Can only trigger once per fight")
                .lang("LastStand").register();
        SOUL_BREAKER = notLoad(IL2Util.TheTrialMonolith) ? null : L2Hostility.REGISTRATE.regTrait
                        ("soulbreaker", () -> new SoulBreakerTrait(ChatFormatting.DARK_BLUE), (id) -> new TraitConfig(
                                id, 100, 100, 1, 50)).desc("When dealing damage, deal an additional 0.01 soul damage")
                .lang("SoulBreaker").register();
        ROOKIE_WRITER = notLoad(IL2Util.Hyperlink) ? null : L2Hostility.REGISTRATE.regTrait
                        ("rookiewriter", () -> new RookieWriterTrait(ChatFormatting.DARK_RED), (id) -> new TraitConfig(
                                id, 100, 100, 1, 50)).desc("When dealing damage, there is an ultra-low chance to Novelize the opponent")
                .lang("RookieWriter").register();
        ENERGY_OF_NINE = notLoad(IL2Util.NoSugar) ? null : L2Hostility.REGISTRATE.regTrait
                        ("energyofnine", () -> new EnergyOfNineTrait(ChatFormatting.RED), (id) -> new TraitConfig(
                                id, 100, 100, 1, 50)).desc("When dealing damage, there is an little-low chance to Tail of Nine the opponent")
                .lang("EnergyOfNine").register();
    }

    public static void register() {
    }
}
