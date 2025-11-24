package net.cosmicapiary.vivid_spirit.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.spectrum.inventories.PedestalScreenHandler;
import de.dafuqs.spectrum.inventories.slots.DisabledSlot;
import de.dafuqs.spectrum.recipe.pedestal.PedestalRecipeTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PedestalScreenHandler.class)
abstract class PedestalScreenHandlerMixin extends AbstractRecipeScreenHandler<Inventory> {

	@Shadow @Final private PedestalRecipeTier pedestalRecipeTier;

	PedestalScreenHandlerMixin(ScreenHandlerType<?> screenHandlerType, int i) {
		super(screenHandlerType, i);
	}

	@Shadow @Override
	public void populateRecipeFinder(RecipeMatcher finder) {

	}

	@Shadow @Override
	public void clearCraftingSlots() {

	}

	@Shadow @Override
	public boolean matches(Recipe<? super Inventory> recipe) {
		return false;
	}

	@Shadow @Override
	public int getCraftingResultSlotIndex() {
		return 0;
	}

	@Shadow @Override
	public int getCraftingWidth() {
		return 0;
	}

	@Shadow @Override
	public int getCraftingHeight() {
		return 0;
	}

	@Shadow @Override
	public int getCraftingSlotCount() {
		return 0;
	}

	@Shadow @Override
	public RecipeBookCategory getCategory() {
		return null;
	}

	@Shadow @Override
	public boolean canInsertIntoSlot(int index) {
		return false;
	}

	@Shadow @Override
	public ItemStack quickMove(PlayerEntity player, int slot) {
		return null;
	}

	@Shadow @Override
	public boolean canUse(PlayerEntity player) {
		return false;
	}

	@Inject(method = "<init>(Lnet/minecraft/screen/ScreenHandlerType;Lnet/minecraft/screen/ScreenHandlerContext;Lnet/minecraft/recipe/book/RecipeBookCategory;ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;IILnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "Lde/dafuqs/spectrum/inventories/PedestalScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 8, shift = At.Shift.AFTER))
	private void disableGemSlots(ScreenHandlerType type, ScreenHandlerContext context, RecipeBookCategory recipeBookCategory, int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate, int pedestalRecipeTier, int maxRecipeTier, BlockPos pedestalPos, CallbackInfo ci) {
		if (this.pedestalRecipeTier == PedestalRecipeTier.valueOf("PRE_GEM")) {
			this.addSlot(new DisabledSlot(inventory, 9, -2000, 77));
			this.addSlot(new DisabledSlot(inventory, 10, -2000, 77));
			this.addSlot(new DisabledSlot(inventory, 11, -2000, 77));
			this.addSlot(new DisabledSlot(inventory, 12, -2000, 77));
			this.addSlot(new DisabledSlot(inventory, 13, -2000, 77));
		}
	}
}
