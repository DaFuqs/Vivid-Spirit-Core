package net.cosmicapiary.vivid_spirit.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.material.EnderAirItem;

@Mixin(EnderAirItem.class)
abstract class EnderAirItemMixin extends Item {

	EnderAirItemMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "onPlayerInteract", at = @At("HEAD"), cancellable = true)
	private static void removeEnderAirInteraction(PlayerEntity player, World world, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
		cir.setReturnValue(TypedActionResult.pass(player.getStackInHand(hand)));
	}
}
