package net.cosmicapiary.vivid_spirit.world.gen;

import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.registries.SpectrumBiomeTags;
import net.cosmicapiary.vivid_spirit.VividSpirit;
import net.cosmicapiary.vivid_spirit.VividStructures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.id.paradiselost.tag.ParadiseLostStructureTags;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModWorldGen {
    public static final RegistryKey<PlacedFeature> LAKE_MUD =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "lake_mud_surface"));
    public static final RegistryKey<PlacedFeature> DD_ZINC_ORE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "dd_zinc_ore"));
    public static final RegistryKey<PlacedFeature> DD_SILVER_ORE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "dd_silver_ore"));
    public static final RegistryKey<PlacedFeature> PL_SHIMMERSTONE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_shimmerstone"));
    public static final RegistryKey<PlacedFeature> PL_ONYX =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_onyx"));
    public static final RegistryKey<PlacedFeature> PL_REDSTONE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "pl_redstone"));

    public static final RegistryKey<PlacedFeature> SAPPHIC_ICE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "sapphic_ice"));
    public static final RegistryKey<PlacedFeature> SAPPHIC_PACKED_ICE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "sapphic_packed_ice"));


    public static final RegistryKey<PlacedFeature> LUMISENE_LAKE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "lumisene_lake"));
    //public static final RegistryKey<PlacedFeature> ONYX_METEOR =
    //        RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(VividSpirit.MOD_ID, "onyx_meteor"));

    public static void addFeatures() {
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.SWAMP),
                GenerationStep.Feature.LAKES, LAKE_MUD);
        BiomeModifications.addFeature(BiomeSelectors.tag(SpectrumBiomeTags.DD_BIOMES),
                GenerationStep.Feature.UNDERGROUND_ORES, DD_ZINC_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(SpectrumBiomeTags.DD_BIOMES),
                GenerationStep.Feature.UNDERGROUND_ORES, DD_SILVER_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.LAKES, LUMISENE_LAKE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, PL_SHIMMERSTONE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, PL_ONYX);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_ORES, PL_REDSTONE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, SAPPHIC_ICE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, SAPPHIC_PACKED_ICE);

        BiomeModifications.addFeature(BiomeSelectors.tag(ParadiseLostStructureTags.VAULT_HAS_STRUCTURE),
                GenerationStep.Feature.UNDERGROUND_STRUCTURES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("topaz_geode")));
    }
}

