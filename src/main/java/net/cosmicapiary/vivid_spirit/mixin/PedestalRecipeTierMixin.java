package net.cosmicapiary.vivid_spirit.mixin;

import de.dafuqs.revelationary.api.advancements.AdvancementHelper;
import de.dafuqs.spectrum.api.item.GemstoneColor;
import de.dafuqs.spectrum.recipe.pedestal.PedestalRecipeTier;
import de.dafuqs.spectrum.registries.SpectrumAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PedestalRecipeTier.class)
abstract class PedestalRecipeTierMixin {
	@Mutable @Shadow @Final private static PedestalRecipeTier[] $VALUES;

	private static PedestalRecipeTier PRE_GEM;

	@Unique private static final Identifier PRE_GEM_ADVANCEMENT = new Identifier("minecraft", "story/mine_stone");

	@Invoker("<init>")
	static PedestalRecipeTier init(String name, int ordinal, Identifier unlockAdvancementId, GemstoneColor[] gemstoneColors) {
		throw new AssertionError();
	}

	@Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lde/dafuqs/spectrum/recipe/pedestal/PedestalRecipeTier;$VALUES:[Lde/dafuqs/spectrum/recipe/pedestal/PedestalRecipeTier;", shift = At.Shift.AFTER))
	private static void addPreGemTier(CallbackInfo ci) {
		PedestalRecipeTier[] newTiers = new PedestalRecipeTier[$VALUES.length + 1];
		//System.arraycopy($VALUES, 0, newTiers, 1, $VALUES.length);

		newTiers[0] = init("PRE_GEM", 0, PRE_GEM_ADVANCEMENT, new GemstoneColor[]{});

		Identifier[] VANILLA_ADVANCMENTS = new Identifier[]{
				SpectrumAdvancements.PLACED_PEDESTAL,
				SpectrumAdvancements.BUILD_BASIC_PEDESTAL_STRUCTURE,
				SpectrumAdvancements.BUILD_ADVANCED_PEDESTAL_STRUCTURE,
				SpectrumAdvancements.BUILD_COMPLEX_PEDESTAL_STRUCTURE
		};

		for (int i = 0; i < $VALUES.length; i++) {
			PedestalRecipeTier tier = $VALUES[i];
			newTiers[i + 1] = init(tier.name(), tier.ordinal() + 1, VANILLA_ADVANCMENTS[i], tier.getAvailableGemstoneColors());
		}

		$VALUES = newTiers;
	}

	@Inject(method = "getHighestUnlockedRecipeTier", at = @At("TAIL"), cancellable = true)
	private static void addToHighestUnlocked(PlayerEntity playerEntity, CallbackInfoReturnable<Optional<PedestalRecipeTier>> cir) {
		if (AdvancementHelper.hasAdvancement(playerEntity, PRE_GEM_ADVANCEMENT)) {
			cir.setReturnValue(Optional.of(PRE_GEM));
		}
	}

	@Inject(method = "hasJustUnlockedANewRecipeTier", at = @At("HEAD"), cancellable = true)
	private static void addToJustUnlocked(@NotNull Identifier advancementIdentifier, CallbackInfoReturnable<Optional<PedestalRecipeTier>> cir) {
		if (advancementIdentifier.equals(PRE_GEM_ADVANCEMENT)) {
			cir.setReturnValue(Optional.of(PRE_GEM));
		}
	}

}
