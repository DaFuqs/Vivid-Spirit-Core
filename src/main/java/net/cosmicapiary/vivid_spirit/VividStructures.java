package net.cosmicapiary.vivid_spirit;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.Structure;

public class VividStructures {
    TagKey<Structure> TOPAZ_GEODE = of("topaz_geode");

    public static final TagKey<Biome> PARADISE_LOST = with("paradise_lost");

    private static TagKey<Structure> of(String id) {
        return TagKey.of(RegistryKeys.STRUCTURE, new Identifier(id));
    }
    private static TagKey<Biome> with(String id) {
        return TagKey.of(RegistryKeys.BIOME, new Identifier(id));
    }

    TagKey<Structure> ONYX_METEOR = of("onyx_meteor");
}
