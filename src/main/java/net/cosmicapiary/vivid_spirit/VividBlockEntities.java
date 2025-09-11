package net.cosmicapiary.vivid_spirit;

import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.blocks.energy.CrystalApothecaryBlockEntity;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class VividBlockEntities {

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, SpectrumCommon.locate(id), FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }

}
