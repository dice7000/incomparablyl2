package net.dice7000.incomparablyl2.common.registry;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.l2hostility.content.config.TraitConfig;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.L2Hostility;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import dev.xkmc.l2library.base.L2Registrate;
import net.dice7000.incomparablyl2.common.trait.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
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
    public static final RegistryEntry<RadioActiveTrait> RADIATION;
    public static final RegistryEntry<SoulBreakerTrait> SOUL_BREAKER;
    public static final RegistryEntry<RookieWriterTrait> ROOKIE_WRITER;
    public static final RegistryEntry<EnergyOfNineTrait> ENERGY_OF_NINE;

    public static final String FantasyEnding = "fantasy_ending";
    public static final String Hyperlink = "hyperdaimc";
    public static final String Mekanism = "mekanism";
    public static final String NoSugar = "nosugar";
    public static final String TheTrialMonolith = "the_trial_monolith";

    public static ChatFormatting formatOrObfuscate(String modid, ChatFormatting format) {
        return (modIsntLoad(modid)/* || */) ? ChatFormatting.OBFUSCATED : format;
    }

    public static boolean modIsntLoad(String modid) {
        return !ModList.get().isLoaded(modid);
    }

    public static final TagKey<EntityType<?>> FORGE_BOSSES = TagKey.create(Registries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath("forge", "bosses"));

    static {
        TRAITS = LHTraits.TRAITS;

        PIERCES_SHIELD = L2Hostility.REGISTRATE.regTrait
                        ("piercesshield", PiercesShieldTrait::new, (id) -> new TraitConfig(
                                id, 80, 100, 5, 20)).desc("When dealing damage, ignore 20% of the target's shield per level")
                .lang("PiercesShield").register();
        DAMAGE_REDUCTION = L2Hostility.REGISTRATE.regTrait
                        ("damagereduction", DamageReductionTrait::new, (id) -> new TraitConfig(
                                id, 80, 100, 4, 20)).desc("When damaged, reduce incoming damage by 20% per level")
                .lang("DamageReduction").register();
        BERSERKER = L2Hostility.REGISTRATE.regTrait
                        ("berserker", BerserkerTrait::new, (id) -> new TraitConfig(
                                id, 100, 100, 3, 30)).desc("Attack damage, armor, and action speed become three times higher")
                .lang("Berserker").register();

        FE_DAMAGE = modIsntLoad(FantasyEnding) ? null : L2Hostility.REGISTRATE.regTrait
                        ("fedamage", FEDamageTrait::new, (id) -> new TraitConfig(
                                id, 20, 100, 5, 20)).desc("When dealing damage, deal an additional FantasyEnding damage")
                .lang("FEDamage").register();
        RUNIC_SHIELD = modIsntLoad(FantasyEnding) ? null : L2Hostility.REGISTRATE.regTrait
                ("runicshield", RunicShieldTrait::new, (id) -> new TraitConfig(
                        id, 50, 100, 5, 50)).desc("When damaged, gain a runic shield that absorbs damage and prevents knockback")
                .lang("RunicShield").register();
        LAST_STAND = modIsntLoad(FantasyEnding) ? null : L2Hostility.REGISTRATE.regTrait
                ("laststand", LastStandTrait::new, (id) -> (new TraitConfig(
                        id, 5, 1000, 1, 10)).addWhitelist((e) -> e.addTag(FORGE_BOSSES)))
                            .desc("When dying, restore to a little health and become invulnerable for a short time. Can only trigger once per fight")
                .lang("LastStand").register();
        RADIATION = modIsntLoad(Mekanism) ? null : L2Hostility.REGISTRATE.regTrait
                ("radiation", RadioActiveTrait::new, (id) -> new TraitConfig(
                        id, 100, 100, 1, 50)).desc("When dealing damage, inflict radioactive contamination on the opponent")
                .lang("Radiation").register();
        SOUL_BREAKER = modIsntLoad(TheTrialMonolith) ? null : L2Hostility.REGISTRATE.regTrait
                        ("soulbreaker", SoulBreakerTrait::new, (id) -> new TraitConfig(
                                id, 100, 100, 1, 50)).desc("When dealing damage, deal an additional 0.01 soul damage")
                .lang("SoulBreaker").register();
        ROOKIE_WRITER = modIsntLoad(Hyperlink) ? null : L2Hostility.REGISTRATE.regTrait
                        ("rookiewriter", RookieWriterTrait::new, (id) -> new TraitConfig(
                                id, 100, 100, 3, 50)).desc("When dealing damage, there is an ultra-low chance to Novelize the opponent")
                .lang("RookieWriter").register();
        ENERGY_OF_NINE = modIsntLoad(NoSugar) ? null : L2Hostility.REGISTRATE.regTrait
                        ("energyofnine", EnergyOfNineTrait::new, (id) -> new TraitConfig(
                                id, 100, 100, 1, 50)).desc("When dealing damage, there is an little-low chance to Tail of Nine the opponent")
                .lang("EnergyOfNine").register();
    }

    public static void register() {
    }
}
