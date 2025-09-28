package net.cosmicapiary.vivid_spirit;

import de.dafuqs.spectrum.SpectrumCommon;
import net.cosmicapiary.vivid_spirit.custom.PigmentSiphonBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class VividBlockEntities {

    public static BlockEntityType<PigmentSiphonBlockEntity> PIGMENT_SIPHON;

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, SpectrumCommon.locate(id), FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }

    public static void register() {
        PIGMENT_SIPHON = register("pigment_siphon", PigmentSiphonBlockEntity::new, VividSpirit.PIGMENT_SIPHON);
}

}
