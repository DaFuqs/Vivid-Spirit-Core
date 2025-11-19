package net.cosmicapiary.vivid_spirit.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FallingBlockEntity.class)
abstract class FallingBlockEntityMixin {

	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/FallingBlockEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;"))
	public ItemEntity damageAnvilWhenDroppingItem(FallingBlockEntity instance, ItemConvertible item) {
		if (item instanceof Block block) {
			if (block.getDefaultState().isOf(Blocks.ANVIL)) {
				instance.dropItem(Blocks.CHIPPED_ANVIL);
			} else if (block.getDefaultState().isOf(Blocks.CHIPPED_ANVIL)) {
				instance.dropItem(Blocks.DAMAGED_ANVIL);
			} else if (!block.getDefaultState().isOf(Blocks.DAMAGED_ANVIL)) {
				instance.dropItem(block);
			}
		}
		return null;
	}
}
