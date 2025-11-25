package net.cosmicapiary.vivid_spirit.mixin;

import de.dafuqs.spectrum.inventories.PedestalScreenHandler;
import de.dafuqs.spectrum.inventories.slots.DisabledSlot;
import de.dafuqs.spectrum.inventories.slots.StackFilterSlot;
import de.dafuqs.spectrum.recipe.pedestal.PedestalRecipeTier;
import de.dafuqs.spectrum.registries.SpectrumItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;
import java.util.stream.Stream;

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

	@Shadow @Final private Inventory inventory;

	@Redirect(method = "<init>(Lnet/minecraft/screen/ScreenHandlerType;Lnet/minecraft/screen/ScreenHandlerContext;Lnet/minecraft/recipe/book/RecipeBookCategory;ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/screen/PropertyDelegate;IILnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "Lde/dafuqs/spectrum/inventories/PedestalScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;"))
	private Slot disableGemSlots(PedestalScreenHandler instance, Slot slot) {
		if (instance.getPedestalRecipeTier() == PedestalRecipeTier.valueOf("PRE_GEM"))
		{
			Stream<ItemStack> GEMSTONE_POWDERS = Stream.of(SpectrumItems.TOPAZ_POWDER.getDefaultStack(), SpectrumItems.AMETHYST_POWDER.getDefaultStack(), SpectrumItems.CITRINE_POWDER.getDefaultStack());
			if (slot instanceof StackFilterSlot filter && GEMSTONE_POWDERS.anyMatch(filter::canInsert)) {
				return this.addSlot(new DisabledSlot(inventory, slot.getIndex(), -2000, 77));
			}
		}
		return this.addSlot(slot);
	}
}
