package net.cosmicapiary.vivid_spirit.mixin;

import de.dafuqs.spectrum.api.block.MultiblockCrafter;
import de.dafuqs.spectrum.api.block.PedestalVariant;
import de.dafuqs.spectrum.blocks.pedestal.PedestalBlockEntity;
import de.dafuqs.spectrum.recipe.pedestal.PedestalRecipeTier;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(PedestalBlockEntity.class)
abstract class PedestalBlockEntityMixin extends LockableContainerBlockEntity implements MultiblockCrafter, RecipeInputProvider, SidedInventory, ExtendedScreenHandlerFactory {
	@Shadow protected PedestalVariant pedestalVariant;

	PedestalBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}

	@Inject(method = "getAvailableSlots", at = @At("TAIL"), cancellable = true)
	private void returnNoSlotsForPreGemTier(Direction side, CallbackInfoReturnable<int[]> cir) {
		if (pedestalVariant.getRecipeTier() == PedestalRecipeTier.valueOf("PRE_GEM")) {
			cir.setReturnValue(new int[0]);
		}
	}

	@Shadow	@Override
	public UUID getOwnerUUID() {
		return null;
	}

	@Shadow @Override
	public void setOwner(PlayerEntity playerEntity) {

	}

	@Shadow @Override
	public void resetUpgrades() {

	}

	@Shadow @Override
	public void calculateUpgrades() {

	}

	@Shadow @Override
	public UpgradeHolder getUpgradeHolder() {
		return null;
	}

	@Shadow @Override
	protected Text getContainerName() {
		return null;
	}

	@Shadow @Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return null;
	}

	@Shadow @Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

	}

	@Shadow @Override
	public int[] getAvailableSlots(Direction side) {
		return new int[0];
	}

	@Shadow @Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return false;
	}

	@Shadow @Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return false;
	}

	@Shadow @Override
	public int size() {
		return 0;
	}

	@Shadow @Override
	public boolean isEmpty() {
		return false;
	}

	@Shadow @Override
	public ItemStack getStack(int slot) {
		return null;
	}

	@Shadow @Override
	public ItemStack removeStack(int slot, int amount) {
		return null;
	}

	@Shadow @Override
	public ItemStack removeStack(int slot) {
		return null;
	}

	@Shadow @Override
	public void setStack(int slot, ItemStack stack) {

	}

	@Shadow @Override
	public boolean canPlayerUse(PlayerEntity player) {
		return false;
	}

	@Shadow @Override
	public void provideRecipeInputs(RecipeMatcher finder) {

	}

	@Shadow @Override
	public void clear() {

	}
}
