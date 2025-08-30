package net.cosmicapiary.vivid_spirit;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VividBlockTags {

    public static final TagKey<Block> PIGMENT_SIPHON_HARVESTABLE = of("pigment_siphon_harvestable");


    private static TagKey<Block> of(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(VividSpirit.MOD_ID, name));
    }
}
